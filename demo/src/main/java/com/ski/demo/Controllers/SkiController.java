package com.ski.demo.Controllers;

import com.ski.demo.Neo4jConnector;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simulator.SkiService;

import java.sql.Time;
import java.util.*;

@Controller
public class SkiController {
  private Neo4jConnector driver = new Neo4jConnector("bolt://localhost:7687", "neo4j",
    "tiger");
  private Map<String, List<String>> liftGraph = new SkiService().generateLiftGraph();

  @GetMapping("/")
  public String index(Model model, @RequestParam(value="name", required=false, defaultValue="Becky") String name) {
    model.addAttribute("name", name);
    return "welcome";
  }

  @GetMapping("/listlifts")
  public String listLifts(Model model) {
    model.addAttribute("liftgraph", liftGraph);
    return "listlifts";
  }

  @GetMapping("/listskiers")
  public String listSkiers(Model model) {
    List<Map> skiers = driver.getAllSkiers();
    model.addAttribute("skiers", skiers);
    return "listskiers";
  }

  @RequestMapping(value="/liftsbyname", method = RequestMethod.GET)
  public String getLiftsByName(Model model, @RequestParam(value="liftName", required = false) String liftName) {
    List<String> connectedLifts = liftGraph.get(liftName);
    Map<String, String> messages = new HashMap<>();

    if (connectedLifts == null) {
      messages.put("success", "Please enter a valid lift name.");
    } else {
      messages.put("success", String.format("Displaying results for listing lifts matching the input liftname{%s}", liftName));
      model.addAttribute("liftentry", new AbstractMap.SimpleEntry<>(liftName, connectedLifts));
    }
    model.addAttribute("messages", messages);
    return "liftsbyname";
  }

  @RequestMapping(value="/topskiersbylift", method = RequestMethod.GET)
  public String getTop10SkiersByLiftName(Model model, @RequestParam(value="liftName", required = false) String liftName
    , @RequestParam(value="season", required = false) String season
    , @RequestParam(value="day", required = false) String day
    , @RequestParam(value="weather", required = false) String weather) {
    List<Map.Entry> skiers = driver.getTop10SkiersByLiftName(liftName, weather, season, day);
    model.addAttribute("skiers", skiers);
    model.addAttribute("season", season);
    model.addAttribute("day", day);
    model.addAttribute("weather", weather);
    Map<String, String> messages = new HashMap<>();
    if (skiers != null && skiers.size() != 0) {
      messages.put("success", String.format("Displaying results for listing top 10 skiers on lift{%s} on season{%s}, day{%s}, weather{%s}:"
        , liftName,  season, day, weather));
    } else {
      messages.put("success", "Please enter a valid lift name.");
    }
    model.addAttribute("messages", messages);
    return "topskiersbylift";
  }

  @RequestMapping(value = "/busiestlifts", method = RequestMethod.GET)
  public String getTop5BusiestLifts(Model model, @RequestParam(value="season", required = false) String season
    , @RequestParam(value="day", required = false) String day
    , @RequestParam(value="weather", required = false) String weather) {
    List<Map.Entry> lifts = driver.getTop5BusiestLifts(weather, season, day);
    model.addAttribute("lifts", lifts);
    model.addAttribute("season", season);
    model.addAttribute("weather", weather);
    model.addAttribute("day", day);

    Map<String, String> messages = new HashMap<>();
    if (lifts != null && lifts.size() != 0) {
      messages.put("success", String.format("Displaying results for listing 5 most daily busiest lift on season{%s}, day{%s}, weather{%s}:"
        , season, day, weather));

    } else {
      messages.put("success", "Please enter a valid ski condition: season, day, weather.");
    }
    model.addAttribute("messages", messages);
    return "busiestliftsbycondition";
  }

//6. Get the top 5 busiest lifts at a certain hour in the skiing day.
  @RequestMapping(value = "/busiestliftsbyhour", method = RequestMethod.GET)
  public String getBusiestLiftByHour(Model model, @RequestParam(value="hour", required = false) String hour
    , @RequestParam(value="season", required = false) String season
    , @RequestParam(value="day", required = false) String day
    , @RequestParam(value="weather", required = false) String weather) {
    Map<String, String> messages = new HashMap<>();

    if (hour != null && hour != "") {
      List<Map.Entry> lifts = driver.getBusiestLiftByHour(hour, weather, season, day);
      model.addAttribute("lifts", lifts);
      model.addAttribute("hour", hour);
      model.addAttribute("season", season);
      model.addAttribute("weather", weather);
      model.addAttribute("day", day);
      if (lifts != null && lifts.size() != 0) {
        messages.put("success", String.format("Displaying results for listing 5 most busiest lift at hour{%s} on season{%s}, day{%s}, weather{%s}:"
          , hour, season, day, weather));
      } else {
        messages.put("success", "Please enter a valid hour (09-14) and valid ski condition: season, day, weather.");
      }
    } else {
      messages.put("success", "Please enter a valid hour (09-14) and valid ski condition: season, day, weather.");

    }
    model.addAttribute("messages", messages);
    return "busiestliftsbyhour";
  }

  //7. Get the top 5 LEAST busiest lifts at a certain hour in the skiing day.
  @RequestMapping(value = "/leastbusiestliftsbyhour", method = RequestMethod.GET)
  public String getLeastBusiestLiftByHour(Model model, @RequestParam(value="hour", required = false) String hour
    , @RequestParam(value="season", required = false) String season
    , @RequestParam(value="day", required = false) String day
    , @RequestParam(value="weather", required = false) String weather) {
    Map<String, String> messages = new HashMap<>();

    if (hour != null && hour != "") {
      List<Map.Entry> lifts = driver.getLeastBusiestLiftByHour(hour, weather, season, day);
      model.addAttribute("lifts", lifts);
      model.addAttribute("hour", hour);
      model.addAttribute("season", season);
      model.addAttribute("weather", weather);
      model.addAttribute("day", day);
      if (lifts != null && lifts.size() != 0) {
        messages.put("success", String.format("Displaying results for listing 5 least busiest lift at hour{%s} on season{%s}, day{%s}, weather{%s}:"
          , hour, season, day, weather));
      } else {
        messages.put("success", "Please enter a valid hour (09-14) and valid ski condition: season, day, weather.");
      }
    } else {
      messages.put("success", "Please enter a valid hour (09-14) and valid ski condition: season, day, weather.");
    }
    model.addAttribute("messages", messages);
    return "leastbusiestliftsbyhour";
  }

  //    8. Get the five most popular lifts for a particular skier. So we could analyze/predict customer patterns.
  @GetMapping("/popularliftbyskier")
  public String getPopularLiftsBySkier(Model model, @RequestParam(value="skierId", required = false) String skierId, @RequestParam(value="season", required = false) String season
    , @RequestParam(value="day", required = false) String day
    , @RequestParam(value="weather", required = false) String weather) {
    List<Map.Entry> liftCnts = driver.getPopularLiftBySkier(skierId, weather, season, day);
    model.addAttribute("liftCnts", liftCnts);
    model.addAttribute("season", season);
    model.addAttribute("weather", weather);
    model.addAttribute("day", day);
    Map<String, String> messages = new HashMap<>();
    if (liftCnts != null && liftCnts.size() != 0) {
      messages.put("success", String.format("Displaying results for listing the five most popular lifts for a skier{%s} on season{%s}, day{%s}, weather{%s}:"
        , skierId,  season, day, weather));
    } else {
      messages.put("success", "Please enter a valid skier ID and valid ski condition: season, day, weather.");
    }
    model.addAttribute("messages", messages);
    return "popularliftbyskier";
  }
}
