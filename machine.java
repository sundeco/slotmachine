import java.util.ArrayList;
/**
 * Write a description of class machine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class machine {
    public static void main(String args[]) {
        double prob2sunflower = 0.1;
        double prob3sunflower = 0.01;
        double prob2sun = 0.1;
        double prob3sun = 0.01;
        double slotTime = 4.3;
        int trials = 100000;
        double sum = 0;
        double fastestTime = 1000;
        double squareSum = 0;
        int[] distribution = new int[22];
        for(int n = 0; n < trials; n++) {
            int sun = 50;
            double time = 9;
            ArrayList<Double> sunflowers = new ArrayList<Double>();
            int sunFallen = 0;
            double timeUntilNextSun = 2.75*Math.random()+4.25;
            while(true) {
                if(Math.abs(time/slotTime - (int)(time/slotTime))<0.001 && sun != 0) { //spin machine
                    sun -= 25;
                    double x = Math.random();
                    if(x<prob2sunflower) {
                        double y = 9.5*Math.random() + 3;
                        sunflowers.add(new Double(y));
                    }
                    else if(x<prob2sunflower+prob3sunflower) {
                        for(int i = 0; i < 3; i++) {
                            double y = 9.5*Math.random() + 3;
                            sunflowers.add(new Double(y));
                        }
                    }
                    else if(x<prob2sunflower+prob3sunflower+prob2sun) {
                        sun += 100;
                    }
                    else if(x<prob2sunflower+prob3sunflower+prob2sun+prob3sun) {
                        sun += 500;
                    }
                }

                //update sunflowers
                for(int i = 0; i < sunflowers.size(); i++) {
                    Double sunflower = sunflowers.get(i);
                    if(sunflower.doubleValue() <= 0) {
                        sun += 25;
                        sunflowers.set(i, new Double(1.5*Math.random()+23.5));
                    }
                    else {
                        sunflowers.set(i, new Double(sunflower.doubleValue()-0.1));
                    }
                }

                //spawn sun from sky
                if(timeUntilNextSun <= 0) {
                    sun += 25;
                    sunFallen++;
                    timeUntilNextSun = Math.min(9.5, 4.25 + 0.1*sunFallen) + Math.random()*2.75;
                }
                else {
                    timeUntilNextSun -= 0.1;
                }

                if(sun>=2000) {
                    break;
                }
                time = time + 0.1;

                // System.out.println("time is " + time);
                // for(Double sunflower:sunflowers) {
                // System.out.println(sunflower.doubleValue());
                // }
                // System.out.println(sun);
                // System.out.println();
            }
            // System.out.println("Final time: " + time);
            // System.out.println("Total sunflowers: " + sunflowers.size());
            if(time < fastestTime) {
                fastestTime = time;
            }
            sum = sum + time;
            //squareSum = squareSum + Math.pow(452-time, 2);
            if(n%10000 == 0) {
                System.out.println(n + " trials" + " done");
            }
            if(time < 90) {
                distribution[0]++;
            }
            else if(time > 720) {
                distribution[21]++;
            }
            else {
                distribution[((int)time-90)/30]++;
            }
        }
        System.out.println("Average time: " + sum/trials);
        System.out.println("Fastest time: " + fastestTime);
        //System.out.println("Standard deviation: " + Math.sqrt(squareSum/(trials-1)));
        for(int i = 0; i < 21; i++) {
            System.out.println(distribution[i]);
        }
    }
}

