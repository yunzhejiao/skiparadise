package simulator;

import simulator.utils.BaseArea;
import simulator.utils.DistributedRandomNumberGenerator;
import simulator.utils.SkiLevel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static simulator.utils.MetricsConstant.*;

public class SkiResort implements ISkiResort {
    private SkiCondition skiCondition;
    private List<Skier> skiers;


    public SkiResort(SkiCondition skiCondition) {
      this.skiCondition = skiCondition;
      this.skiers = new ArrayList<>();
      generateSkiers();
    }


    public void generateSkiers() {
        int skierNumber = this.getSkiCondition().getSkierNumber();
        for (int i = 0; i < skierNumber; i++) {
            Time startTime = generateRandStartTime();//create startTime for each skier
            boolean haveLunch = (i % 2 == 0);//half skiers have lunch
            BaseArea baseArea = BaseArea.values()[getDistributionFromCSV()];
            addToSkiers(startTime, haveLunch, baseArea);
        }
    }


    private void addToSkiers(Time startTime, boolean haveLunch, BaseArea baseArea) {
        UUID uuid = UUID.randomUUID();//create a id for skier
        SkiLevel skiLevel = SkiLevel.values()[new Random().nextInt((SkiLevel.values().length))];//randomly assigned ski level
        Skier skier = new Skier(uuid, skiLevel, haveLunch, baseArea, startTime);
        this.skiers.add(skier);
    }

    private int getDistributionFromCSV() {
        DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
        drng.addNumber(BaseArea.valueOf("BLACKCOMB_GONDOLA").ordinal(), BlackcombProb);
        drng.addNumber(BaseArea.valueOf("WHISTLER_GONDOLA").ordinal(), WhistlerProb);
        drng.addNumber(BaseArea.valueOf("CREEKSIDE_GONDOLA").ordinal(), 1- BlackcombProb-WhistlerProb);
        return DistributedRandomNumberGenerator.getDistributedRandomNumber();
    }

    private Time generateRandStartTime() {
        if (new Random().nextInt(2) == 0) {//50% skiers start at peak hour
            int minute = new Random().nextInt(minuteOfPeakTime);//minuteOfPeakTime = 31;
            int second = new Random().nextInt(SECONDS);//minuteOfPeakTime = 31;

          return Time.valueOf(hourOfPeakTime +":"+ minute+":" + second);//hourOfPeakTime = 9;
        } else {//the rest start randomly at each open hour
            int minute = 0;
            int[] hourArray = {(new Random().nextInt((endOfMorningHour - hourOfPeakTime) + 1) + hourOfPeakTime),//endOfMorningHour = 12;
                    (new Random().nextInt((endOfDayHour - beginningOfAfternoonHour) + 1) + beginningOfAfternoonHour)};//beginningOfAfternoonHour = 13;endOfDayHour = 14;

            int hour = hourArray[new Random().nextInt(hourArray.length)];
            if (hour == hourOfPeakTime) {
                minute = new Random().nextInt((lastMinuteBeforeNextHour - minuteOfPeakTime) + 1) + minuteOfPeakTime;
            } else {
                minute = new Random().nextInt(lastMinuteBeforeNextHour);//lastMinuteBeforeNextHour = 59;
            }
            Time startTime = Time.valueOf(hour +":"+ minute+":"+ new Random().nextInt(SECONDS));
            return startTime;
        }
    }

    public SkiCondition getSkiCondition() {
        return skiCondition;
    }

    public List<Skier> getSkiers() {
        return skiers;
    }
}