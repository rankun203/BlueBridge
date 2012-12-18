package com.mindfine.myRobot;

import java.util.HashMap;

public class MyRobot {
    private static int [] oldPoint = new int[2];
    private static int [] newPoint = new int[2];
    private static int actionLength = 0;
    private static int curDirection = 0;//0:ahead, 1:right, 2:back, 3:left

    public static void main(String [] args){
        
System.out.println("I'm robot");
System.out.println(new MyRobot().setOldPoint(new int[]{0, 0}).move("15L10R5LRR10R").getDistance());

    }

    public double getDistance(){
System.out.println("oldPoint:" + "(" + oldPoint[0] + "," + oldPoint[1] + ")");
System.out.println("newPoint:" + "(" + newPoint[0] + "," + newPoint[1] + ")");
        double disY = (newPoint[1] - oldPoint[1]);
        double disX = (newPoint[0] - oldPoint[0]);
        double distance = Math.sqrt(disX*disX + disY*disY);
        return Math.abs(distance);
    }
    public MyRobot setOldPoint(int [] oldPoint){
        MyRobot.oldPoint = oldPoint;
        return this;
    }
    public static HashMap<Integer, Object> actionAnalyse(String actionStr){
        HashMap<Integer, Object> actionStep = new HashMap<Integer, Object>();

        int curStep = 0;
        int numLength = 0;//指定“走多少米”的数字的长度
        for (int i = 0; i <= actionStr.length(); i++) {
            if(i == actionStr.length()){//最后一个如果是数字，则最后获取动作的属性存到HashSet中。
                if(numLength != 0){
                    actionStep.put(curStep, Integer.parseInt(actionStr.substring(i - numLength, i)));
                    numLength = 0;
                    curStep ++;
                    continue;
                }
                break;
            }
            char curChar = actionStr.charAt(i);
            if(curChar >= 65 && curChar <= 90){
                if(numLength != 0){
                    actionStep.put(curStep, Integer.parseInt(actionStr.substring(i - numLength, i)));
                    numLength = 0;
                    curStep ++;
                }
                actionStep.put(curStep, curChar);
                curStep ++;
            } else {
                numLength ++;
            }
        }

        return actionStep;
    }
    public MyRobot move(String action){
        HashMap<Integer, Object> actionHashMap = actionAnalyse(action);
        for(Integer i: actionHashMap.keySet()){
            Object curAction = actionHashMap.get(i);

            if(curAction instanceof Character){
                if((Character)curAction == 'L' || (Character)curAction == 'l'){
                    curDirection --;
                    if(curDirection < 0){
                        curDirection = (curDirection + 4 * 10000) % 4;
                    }
                } else if((Character)curAction == 'R' || (Character)curAction == 'r'){
                    curDirection ++;
                    if(curDirection > 3){
                        curDirection = curDirection % 4;
                    }
                }
            } else if(curAction instanceof Integer){
                switch (curDirection){
                    case 0:newPoint[1] = newPoint[1] + (Integer)curAction; break;
                    case 1:newPoint[0] = newPoint[0] + (Integer)curAction; break;
                    case 2:newPoint[1] = newPoint[1] - (Integer)curAction; break;
                    case 3:newPoint[0] = newPoint[0] - (Integer)curAction; break;
                }
            }
System.out.println("after action:" + curAction + " newPoint:" + "(" + newPoint[0] + "," + newPoint[1] + ")");
        }
        return this;
    }

}
