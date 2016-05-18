package com.flextronics.cn.util;

import java.util.ArrayList;
import java.util.List;

public class HearingUtil {
	
	public static final class DataConversion{
		/**
		 * List集合转换Int数组
		 * @param list
		 * @return
		 */
		public static int[] listToInteger(List<Integer> list){
			int[] temp=new int[list.size()];
			for (int i = 0; i < temp.length; i++) {
				temp[i]=list.get(i);
			}
			return temp;
		}
		
		/**
		 * List集合转换String数组
		 * @param list
		 * @return
		 */
		public static String[] listToString(List<Integer> list){
			String[] temp=new String[list.size()];
			for (int i = 0; i < temp.length; i++) {
				temp[i]=list.get(i).toString();
			}
			return temp;
		}
		
		/**
		 * String数组转换Int数组
		 * @param array
		 * @return
		 */
		public static int[] stringArrayToInteger(String[] array){
			int[] temp=new int[array.length];
			for (int i = 0; i < array.length; i++) {
				temp[i]=Integer.parseInt(array[i]);
			}
			return temp;
		}
		
		/**
		 * Int数组转换List集合
		 * @param params
		 * @return
		 */
		public static List<Integer> integerArrayToList(int[] params){
			List<Integer> temp=new ArrayList<Integer>();
			for (int i = 0; i < params.length; i++) {
				temp.add(params[i]);
			}
			return temp;
		}
	}
	
	public static String[] getRhythmAnswerArray(String [] answerArray){
		String [] rhythmAnswerArray=null;
		List<Integer> list=new ArrayList<Integer>();
		for (int i = 0; i < answerArray.length; i++) {
			if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_ONE)
			{
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_TWO){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_THREE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_FOUR){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_FIVE){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_SIX){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_SEVEN){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarThreeBeat.T3_EIGHT){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_ONE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_TWO){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_THREE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_FOUR){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_FIVE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_SIX){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_SEVEN){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_EIGHT){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_NINE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_TEN){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_ELEVEN){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_TWELVE){
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_THIRTEEN){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_FOURTEEN){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_FIFTEEN){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.ONE_BEAT);
			}else if(Integer.parseInt(answerArray[i])==HearingConstants.Rhythm.OneBarFourBeat.T4_SIXTEEN){
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
				list.add(HearingConstants.Beat.HALF_BEAT);
			}
		}
		rhythmAnswerArray=new String[list.size()];
		for (int i = 0; i < rhythmAnswerArray.length; i++) {
			rhythmAnswerArray[i]=list.get(i).toString();
		}
		return rhythmAnswerArray;
	}
}
