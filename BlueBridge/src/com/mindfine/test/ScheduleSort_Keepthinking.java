package com.mindfine.test;


public class ScheduleSort_Keepthinking {  
    private static int[][] types;  
    public static void main(String[] args) {  
        //根据条件：  
        //1.必须工作5天，休息2天  
        //2.不能连续工作超过3天  
        //根据以上两个条件可以确定每个人的时间安排只可能有7种可能  
        //即以下这七种情况：（0表示休息，1表示上班）  
//      0 1 1 1 0 1 1   
//      1 0 1 1 1 0 1   
//      1 1 0 1 1 1 0   
//      0 1 1 0 1 1 1   
//      1 0 1 1 0 1 1   
//      1 1 0 1 1 0 1   
//      1 1 1 0 1 1 0  
        types=new int[7][7];   
        int j=0;  
        //初始化types数组，i表示第一个0出现的下标，j表示第二个0出现的下标  
        for(int i=0;i<7;i++){  
            j=(i+4)%7;  
            for(int t=0;t<7;t++){  
                if(t==i||t==j){  
                    types[i][t]=0;  
                }else{  
                    types[i][t]=1;  
                }  
            }  
        }  
        //types初始化结束  
          
        //测试所有情况,每个人都可以从7种可能的工作情况中选一种，所以一共有5^7种可能  
        for(int a=0;a<7;a++){//a=i表示A采用第i种工作情况  
            // A周三必须上班  
            if(types[a][2]==0)continue;  
            for(int b=0;b<7;b++){  
                // B在周日那天必须休息  
                if(types[b][6]==1)continue;  
                for(int c=0;c<7;c++){  
                    // A C 一周中必须至少有4天能见面（即同时上班）  
                    if(!validateAC(a, c))continue;  
                    for(int d=0;d<7;d++){  
                        //D在周日那天必须休息  
                        if(types[d][6]==1)continue;  
                        //任何一天，必须保证ABCD中至少有2人上班  
                        if(!validateABCD(a, b, c, d))continue;  
                        for(int e=0;e<7;e++){  
                            //E 周三必须上班,周日那天必须休息  
                            if(types[e][2]==0||types[e][6]==1)continue;  
                            // 一周中，至少有3天所有人都是上班的  
                            if(!validateAll(a, b, c, d, e))continue;  
                            print(a, b, c, d, e);  
                        }  
                    }  
                }  
            }  
        }  
    }  
    //判断ABCD每天是否至少有2人上班，是返回true，否返回false  
    public static boolean validateABCD(int a,int b,int c,int d){  
        for(int i=0;i<7;i++){  
            if(types[a][i]+types[b][i]+types[c][i]+types[d][i]<2)return false;  
        }  
        return true;  
    }  
    //判断一周中是否至少有3天所有人都是上班的  
    public static boolean validateAll(int a,int b,int c,int d,int e){  
        int days=0;  
        for(int i=0;i<7;i++){  
            if(types[a][i]+types[b][i]+types[c][i]+types[d][i]+types[e][i]==5)days++;  
        }  
        return days>=3;  
    }  
    //判断一周中是否A,C至少有4天能见面  
    public static boolean validateAC(int a,int c){  
        int days=0;  
        for(int i=0;i<7;i++){  
            if(types[a][i]+types[c][i]==2)days++;  
        }  
        return days>=4;  
    }  
    //打印结果  
    public static void print(int a,int b,int c,int d,int e){  
        for(int i=0;i<7;i++)System.out.print(types[a][i]);  
        System.out.println();  
        for(int i=0;i<7;i++)System.out.print(types[b][i]);  
        System.out.println();  
        for(int i=0;i<7;i++)System.out.print(types[c][i]);  
        System.out.println();  
        for(int i=0;i<7;i++)System.out.print(types[d][i]);  
        System.out.println();  
        for(int i=0;i<7;i++)System.out.print(types[e][i]);  
        System.out.println();  
          
        System.out.println();  
    }  
}  