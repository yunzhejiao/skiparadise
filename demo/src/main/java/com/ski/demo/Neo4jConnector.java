package com.ski.demo;

import org.neo4j.driver.v1.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4jConnector implements AutoCloseable {
  private final Driver driver;

  public Neo4jConnector(String uri, String user, String password) {
    driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
  }

  @Override
  public void close() {
    driver.close();
  }

  public void loadCSV(String outputCSV) throws IOException {
    createNodes(outputCSV);
    createRelations(outputCSV);
  }

  public void createNodes(String outputCSV) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(outputCSV))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        Session session = driver.session();
        List<Record> r = session.writeTransaction(tx -> {
          StatementResult loadingStatement= tx.run(
            "MERGE (s:Skier{sid: $sid}) \n MERGE (p:Lift {liftName: $name}) \n " ,
            parameters("sid", values[0], "name", values[1]));
          return loadingStatement.list();
        });
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void createRelations(String outputCSV) throws IOException {
    String[] words = outputCSV.split("-");
    try (BufferedReader br = new BufferedReader(new FileReader(outputCSV))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        Session session = driver.session();
        List<Record> r = session.writeTransaction(tx -> {
          StatementResult loadingStatement= tx.run(

              "MATCH (s :Skier{sid : $sid})\n MATCH (p : Lift {liftName : $name})\n " +
              "MERGE (s) -[r:TAKEN{timeIn:$timeIn, weekday:$weekday, season:$season, weather:$weather}]->(p) \n" + "RETURN r",
            parameters("sid", values[0], "name", values[1], "timeIn", values[2],
              "weekday", words[3], "weather", words[1], "season", words[2]));
          return loadingStatement.list();
        });
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }


  public List<Map> getAllLifts() {
    List<Map> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult result = tx.run("MATCH (p:Lift) RETURN p");
        return result.list();
      });
      for (Record r : lifts) {
        res.add(r.get("p").asMap());
      }
    }
    return res;
  }

  public List<Map> getAllSkiers() {
    List<Map> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> skiers = session.readTransaction(tx -> {
        StatementResult result = tx.run("MATCH (p:Skier) RETURN p LIMIT 10");
        return result.list();
      });
      for (Record r : skiers) {
        res.add(r.get("p").asMap());
      }
    }
    return res;
  }

  public List getLiftsByName(String liftName) {
    List res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult result = tx.run("MATCH (p:Lift) WHERE p.liftName=$liftName RETURN p.liftName as lift",
          parameters("liftName", liftName));
        return result.list();
      });
      for (Record r : lifts) {
        res.add(r.get("lift"));
      }
    }
    return res;
  }

  public List<Map.Entry> getTop10SkiersByLiftName(String liftName, String weather, String season, String day) {
    List<Map.Entry> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult result = tx.run("MATCH (s:Skier)-[r:TAKEN]->(l:Lift) WHERE l.liftName=$liftName and " +
            "r.weather=$weather and r.season=$season and r.weekday=$day " +
            "RETURN DISTINCT s.sid as sid,count(s) as cnt order by cnt DESC limit 10",
          parameters("liftName", liftName, "weather", weather, "season", season, "day", day));
        return result.list();
      });
      for (Record r : lifts) {
        res.add(new AbstractMap.SimpleEntry<>(r.get("sid"), r.get("cnt")));
      }
    }
    return res;
  }

  public List<Map.Entry> getTop5BusiestLifts(String weather, String season, String day) {
    List<Map.Entry> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult query = tx.run("match (s:Skier) -[r:TAKEN]->(l:Lift) \n" +
            "where r.weather=$weather and r.season=$season and r.weekday=$day \n" +
            "RETURN l.liftName as lift, COUNT(r) as cnt ORDER BY COUNT(r) DESC LIMIT 5",
          parameters("weather", weather, "season", season, "day", day));
        return query.list();
      });

      for (Record r : lifts) {
        res.add(new AbstractMap.SimpleEntry<>(r.get("lift"), r.get("cnt")));
      }

    }

    return res;
  }


  public List<Map.Entry> getBusiestLiftByHour(String hour, String weather, String season, String day) {
    List<Map.Entry> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult query = tx.run("match (s:Skier) -[r:TAKEN]->(l:Lift) \n" +
            "where r.weather=$weather and r.season=$season and r.weekday=$weekday and r.timeIn STARTS WITH $hour\n" +
            "RETURN l.liftName as lift, COUNT(r) as cnt ORDER BY COUNT(r) DESC LIMIT 5",
          parameters("weather", weather, "season", season, "weekday", day, "hour", hour));
        return query.list();
      });

      for (Record r : lifts) {
        res.add(new AbstractMap.SimpleEntry<>(r.get("lift"), r.get("cnt")));
      }

    }

    return res;
  }

  public List<Map.Entry> getLeastBusiestLiftByHour(String hour, String weather, String season, String day) {
    List<Map.Entry> res = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult query = tx.run("match (s:Skier) -[r:TAKEN]->(l:Lift) \n" +
            "where r.weather=$weather and r.season=$season and r.weekday=$weekday and r.timeIn STARTS WITH $hour\n" +
            "RETURN l.liftName as lift, COUNT(r) as cnt ORDER BY COUNT(r) ASC LIMIT 5",
          parameters("weather", weather, "season", season, "weekday", day, "hour", hour));
        return query.list();
      });

      for (Record r : lifts) {
        res.add(new AbstractMap.SimpleEntry<>(r.get("lift"), r.get("cnt")));
      }

    }

    return res;
  }


  public List<Map.Entry> getPopularLiftBySkier(String skierId, String weather, String season, String day) {
    List<Map.Entry> liftCnts = new ArrayList<>();
    try (Session session = driver.session()) {
      List<Record> lifts = session.readTransaction(tx -> {
        StatementResult query = tx.run("match (s:Skier) -[r:TAKEN]->(l:Lift) \n" +
            "where r.weather=$weather and r.season=$season and r.weekday=$weekday and s.sid=$skierId\n" +
            "RETURN l.liftName as lift, COUNT(r) as cnt ORDER BY COUNT(r) DESC LIMIT 5",
          parameters("weather", weather, "season", season, "weekday", day, "skierId", skierId));
        return query.list();
      });

      for (Record r : lifts) {
        liftCnts.add(new AbstractMap.SimpleEntry<>(r.get("lift").toString(), r.get("cnt").asInt()));
      }
    }
    return liftCnts;
  }
}