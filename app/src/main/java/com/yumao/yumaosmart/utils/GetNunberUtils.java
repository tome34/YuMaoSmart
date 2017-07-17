package com.yumao.yumaosmart.utils;

/**
 * Created by kk on 2017/7/13.
 * 获取编号
 */

public class GetNunberUtils {

    //获得编号
    public static String getNumber(int a) {
        char[] numChar =new char[2];
        String s = String.valueOf(a);
            LogUtils.d("编号的位数:"+s);
        char[] chars = s.toCharArray();
//        LogUtils.d("chars:"+chars[0]+" "+chars[1]);
        if(chars.length ==1){

        if (chars[0] == '0'){
            numChar[1] ='X' ;
        }else if(chars[0] == '1'){
            numChar[1] ='A' ;
        }else if(chars[0] == '2'){
            numChar[1] ='B' ;
        }else if(chars[0] == '3'){
            numChar[1] ='C' ;
        }else if(chars[0] == '4'){
            numChar[1] ='D' ;
        }else if(chars[0] == '5'){
            numChar[1] ='E' ;
        }else if(chars[0] == '6'){
            numChar[1] ='F' ;
        }else if(chars[0] == '7'){
            numChar[1] ='G' ;
        }else if(chars[0] == '8'){
            numChar[1] ='H' ;
        }else if(chars[0] == '9'){
            numChar[1] ='I' ;
        }
          numChar[0] = 'X';

        }else {
            if (chars[0] == '0'){
                numChar[0] ='X' ;
            }else if(chars[0] == '1'){
                numChar[0] ='A' ;
            }else if(chars[0] == '2'){
                numChar[0] ='B' ;
            }else if(chars[0] == '3'){
                numChar[0] ='C' ;
            }else if(chars[0] == '4'){
                numChar[0] ='D' ;
            }else if(chars[0] == '5'){
                numChar[0] ='E' ;
            }else if(chars[0] == '6'){
                numChar[0] ='F' ;
            }else if(chars[0] == '7'){
                numChar[0] ='G' ;
            }else if(chars[0] == '8'){
                numChar[0] ='H' ;
            }else if(chars[0] == '9'){
                numChar[0] ='I' ;
            }

            if (chars[1] == '0') {
                numChar[1] = 'X';
            } else if (chars[1] == '1') {
                numChar[1] = 'A';
            } else if (chars[1] == '2') {
                numChar[1] = 'B';
            } else if (chars[1] == '3') {
                numChar[1] = 'C';
            } else if (chars[1] == '4') {
                numChar[1] = 'D';
            } else if (chars[1] == '5') {
                numChar[1] = 'E';
            } else if (chars[1] == '6') {
                numChar[1] = 'F';
            } else if (chars[1] == '7') {
                numChar[1] = 'G';
            } else if (chars[1] == '8') {
                numChar[1] = 'H';
            } else if (chars[1] == '9') {
                numChar[1] = 'I';
            }
        }

        LogUtils.d("111:"+numChar[0]+" "+numChar[1]);


        return new String(numChar);
    }
}
