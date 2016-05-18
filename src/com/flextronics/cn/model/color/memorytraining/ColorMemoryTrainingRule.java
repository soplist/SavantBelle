package com.flextronics.cn.model.color.memorytraining;

import com.flextronics.cn.model.BaseRule;

/**<b>C-颜色记忆训练</b><br>
 * 答题规则
 * 
 * @author ZhangGuoYin
 *
 */
public class ColorMemoryTrainingRule extends BaseRule {
	
	public static int getCumulativeErrorCount(int bits){
		
		if (bits < 3) {
			return 0;
		}else if (bits>=3 && bits<=7) {
			return 7;
		}else if (bits>=8 && bits<=10) {
			return 8;
		}else if (bits>=11 && bits<=12) {
			return 9;
		}else if (bits>=13 && bits<=14) {
			return 10;
		}else if (bits>=15 && bits<=16) {
			return 11;
		}else if (bits>=17 && bits<=18) {
			return 12;
		}else if (bits>=19 && bits<=20) {
			return 13;
		}else if (bits>=21 && bits<=22) {
			return 14;
		}else if (bits>=23 && bits<=24) {
			return 15;
		}else if (bits>=25 && bits<=26) {
			return 16;
		}else if (bits>=27 && bits<=28) {
			return 17;
		}else if (bits>=29 && bits<=30) {
			return 18;
		}else if (bits>=31 && bits<=32) {
			return 19;
		}else if (bits>=33 && bits<=34) {
			return 20;
		}else if (bits>=35 && bits<=36) {
			return 21;
		}else {
			return 0;
		}
	}
}