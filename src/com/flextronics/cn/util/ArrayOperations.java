package com.flextronics.cn.util;

import java.util.Random;

/**数组操作工具类
 * 
 * @author ZhangGuoYin
 *
 */
public class ArrayOperations {

	/**取得元素在数组中的第一次出现的位置
	 * 
	 * @param elements 数组
	 * @param element 元素
	 * @return 第一次出现的位置, 没有返回-1
	 */
	public static int indexInElement(String[] elements, String element){

		if (elements==null || element==null) {
			return -1;
		}

		for (int i = 0; i < elements.length; i++) {
			if (element.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**取得元素在数组中的第一次出现的位置
	 * 
	 * @param elements 数组
	 * @param element 元素
	 * @return 第一次出现的位置, 没有返回-1
	 */
	public static int indexInElement(int[] elements, int element){

		if (elements == null) {
			return -1;
		}

		for (int i = 0; i < elements.length; i++) {
			if (element == elements[i]) {
				return i;
			}
		}
		return -1;
	}

	/** 取得该元素第n次在数组中出现的位置(该元素在数组中有多份)
	 * @param elements
	 * @param element
	 * @param No	第n次，从1开始
	 * @return
	 */
	public static int indexInElement(String[] elements, String element, int No){

		Integer count = 0;
		if (elements==null || element==null || No==0 || No>element.length()) {
			return -1;
		}

		for (int i = 0; i < elements.length; i++) {
			if (element.equals(elements[i])) {
				count ++;
				if (count == No) {
					return i;
				}
			}
		}
		return -1;
	}

	/**取得元素在数组中的个数
	 * 
	 * @param elements
	 * @param element
	 * @return
	 */
	public static int getElementCountInElement(String[] elements, String element){

		Integer count = 0;
		if (elements==null || element==null || element.trim().length()==0) {
			return count;
		}

		for (int i = 0; i < elements.length; i++) {
			if (element.equals(elements[i])) {
				count++;
			}
		}
		return count;
	}


	/**将两个数组简单相加, 仅仅将两个数组中的内容合并到一个数组中，并不去除重复的元素
	 * 
	 * @param part1	数组1
	 * @param part2	数组2
	 * @return	合并后的数组(并不去除重复元素)
	 */
	public static String[] addElements(String[] part1, String[] part2){
		String[] elements;
		if (part1==null && part2!=null) {
			elements = new String[part2.length];
			for (int i = 0; i < part2.length; i++) {
				elements[i] = part2[i];
			}
		}else if (part1!=null && part2==null) {
			elements = new String[part1.length];
			for (int i = 0; i < part1.length; i++) {
				elements[i] = part1[i];
			}
		}else {
			elements = new String[part1.length+part2.length];
			int i=0;
			for (String element : part1) {
				elements[i] = element;
				i++;
			}
			for (String element : part2) {
				elements[i] = element;
				i++;
			}
		}
		return elements;
	}

	/**将两个数组相减
	 * 
	 * @param all	被减数组
	 * @param part1	减数组
	 * @return	
	 */
	public static String[] otherElements(String[] all, String[] part1){

		if (all==null) {
			return null;
		}else if (part1 == null) {
			return all;
		}else if (all.length<part1.length) {
			return null;
		}else {
			String[] others = new String[all.length-part1.length];
			int i = 0;
			for(String element : all){
				if (!inElements(part1, element)) {
					others[i] = element;
					i++;
				}
			}
			return others;
		}
	}

	/**判断一个元素是否存在于某一数组
	 * 
	 * @param elements	数组
	 * @param element	元素
	 * @return true--存在;<br>false--不存在;
	 */
	public static boolean inElements(String[] elements, String element){

		if (elements==null || element==null) {
			return false;
		}

		for(String _element : elements){
			if (_element.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**判断一个数组是否存在于某一数组
	 * 
	 * @param elements	原数组
	 * @param chlidElements	需要判断的数组
	 * @return true--存在;<br>false--不存在;
	 */
	public static boolean inElements(String[] elements, String[] chlidElements){

		if (elements==null || chlidElements==null) {
			return false;
		}

		for(String _element : chlidElements){
			if (!inElements(elements, _element)) {
				return false;
			}
		}
		return true;
	}


	/**从一个数组中随机取得一个元素
	 * 
	 * @param elements	数组
	 * @return
	 */
	public static String getRedomElementFromElements(String[] elements){

		if (elements==null) {
			return null;
		}		
		return elements[CommonUtil.radomFromZero(elements.length)];
	}
	
	public static int getRedomElementFromElements(int[] elements){

		if (elements==null) {
			return 0;
		}		
		return elements[CommonUtil.radomFromZero(elements.length)];
	}

	/**从一个数组中随机取得指定个数的元素,如果指定数目不大于数组长度，则返回的元素不允许有重复数据；
	 * 如果指定数目大于数组长度，则返回的元素允许有重复的
	 * 
	 * @param elements	原数组
	 * @param count	须取得的数目
	 * @return
	 */
	public static String[] getRedomElementFromElements(String[] elements, int count){

		if (elements==null || count<1) {
			return null;
		}

		String[] results = new String[count];

		if (count <= elements.length) {
			for (int i = 0; i < count; i++) {
				int index = CommonUtil.radomFromZero(elements.length);
				String element = elements[index];
				results[i] = element;
				elements = removeElementFromElements(elements, index);
			}
		}else if (count > elements.length) {
			for (int i = 0; i < count; i++) {
				results[i] = elements[CommonUtil.radomFromZero(elements.length)];
			}
		}

		return results;
	}

	/**从一个数组中随机取得指定个数的元素,如果指定数目不大于数组长度，则返回的元素不允许有重复数据；
	 * 如果指定数目大于数组长度，则返回的元素允许有重复的
	 * 
	 * @param elements	原数组
	 * @param count	须取得的数目
	 * @return
	 */
	public static int[] getRedomElementFromElements(int[] elements, int count){

		if (elements==null || count<1) {
			return null;
		}

		int[] results = new int[count];

		if (count <= elements.length) {
			for (int i = 0; i < count; i++) {
				int index = CommonUtil.radomFromZero(elements.length);
				int element = elements[index];
				results[i] = element;
				elements = removeElementFromElements(elements, index);
			}
		}else if (count > elements.length) {
			for (int i = 0; i < count; i++) {
				results[i] = elements[CommonUtil.radomFromZero(elements.length)];
			}
		}

		return results;
	}
	
	/**移除数组中制定位置的数
	 * 
	 * @param elements	原数组
	 * @param index	制定位置
	 * @return
	 */
	public static String[] removeElementFromElements(String[] elements, int index){

		if (elements==null || index<0 || index>elements.length-1 || elements.length==1) {
			return null;
		}

		String results[] = new String[elements.length-1];
		int i = 0;
		for (int j = 0; j < elements.length; j++) {
			if (j == index) {
				continue;
			}
			results[i] = elements[j];
			i++;
		}

		return results;		
	}

	/**移除数组中制定位置的数
	 * 
	 * @param elements	原数组
	 * @param index	制定位置
	 * @return
	 */
	public static int[] removeElementFromElements(int[] elements, int index){

		if (elements==null || index<0 || index>elements.length-1 || elements.length==1) {
			return null;
		}

		int results[] = new int[elements.length-1];
		int i = 0;
		for (int j = 0; j < elements.length; j++) {
			if (j == index) {
				continue;
			}
			results[i] = elements[j];
			i++;
		}

		return results;		
	}
	
	/**打乱一个数组
	 * 
	 * @param elements
	 * @return
	 */
	public static String[] redomElements(String[] _elements){

		if (_elements == null) {
			return null;
		}

		String[] elements = new String[_elements.length];
		for (int i = 0; i < _elements.length; i++) {
			elements[i] = _elements[i];
		}
		String[] values = new String[elements.length];
		Random ran = new Random();
		for (int i = 0; i < elements.length; i++) {

			// create a random index
			int j = ran.nextInt(elements.length - i);
			// get the value at this index in elements
			values[i] = elements[j];
			// move the value at the last position to this position 
			elements[j] = elements[elements.length - 1 - i];
		}
		
		return values;
	}
	
	/**将一个int型的数组转为String型
	 * 
	 * @param array
	 * @return
	 */
	public static String[] toStringArray(int[] array){
		if (array == null) {
			return null;
		}else {
			String[] results = new String[array.length];
			for (int i = 0; i < array.length; i++) {
				results[i] = String.valueOf(array[i]);
			}
			return results;
		}
	}
	
	/**
	 * 从一个数组中取指定个数的元素, 当个数大于数组本身大小时, 则元素重复, 但是前后不连续;
	 * 尽力让元素不重复, 提高随机性
	 * @param _elements
	 * @param bits
	 * @return
	 */
	public static String[] redomElements(String[] _elements, int bits){
		if (_elements == null || bits<1) {
			return null;
		}
		
		String[] elements = null;
		if (_elements.length == 1) {
			elements = new String[bits];
			for (int i = 0; i < elements.length; i++) {
				elements[i] = _elements[0];
			}
			return elements;
		}
		
		int multiple = bits / _elements.length;
		int more = bits % _elements.length;
		for (int i = 0; i < multiple; i++) {
			String[] e;
			if (i == 0) {
				e = redomElements(_elements);
			}else {
				do {
					e = redomElements(_elements);				
				} while (elements[elements.length-1].equals(e[0]));
			}
			elements = addElements(elements, e);
		}
		
		if (more > 0) {

			String[] e;
			String[] e2 = new String[more];
			if (elements == null) {
				e = redomElements(_elements);
			}else {
				do {
					e = redomElements(_elements);				
				} while (elements[elements.length-1].equals(e[0]));
			}
			
			for (int i = 0; i < more; i++) {
				e2[i] = e[i];
			}
			elements = addElements(elements, e2);
		}
		
		return elements;
	}	
	
	/**
	 * 从一个数组中取指定个数的元素, 当个数大于数组本身大小时, 则元素重复, 但是前后不连续;
	 * 尽力让元素不重复, 提高随机性
	 * @param _elements
	 * @param bits
	 * @return
	 */
	public static String[] redomElements(String[][] _elements, int bits){
		if (_elements == null || bits<1) {
			return null;
		}
		
		String[] elements = null;
		int multiple = bits / _elements.length;
		int more = bits % _elements.length;
		int _bits = 0;
		
		//将数组一维打乱
		String[] indexs = new String[_elements.length];
		for (int i = 0; i < indexs.length; i++) {
			indexs[i] = i + "";
		}
		indexs = redomElements(indexs);
		
		for (int i = 0; i < indexs.length; i++) {
			if (more>0 && i <= more-1) {
				_bits = multiple + 1;
			}else if (multiple > 0) {
				_bits = multiple;
			}else {
				_bits = 0;
			}
			if (_bits>0) {
				elements = addElements(elements, 
						redomElements(_elements[Integer.valueOf(indexs[i])], _bits));				
			}			
		}
		return redomElements(elements, bits);
	}	
	
	
	/**
	 * 对数组进行洗牌
	 * 
	 * @param array
	 */
	public static int[] shuffle(int[] array) {
		Random random = new Random();
		int length = array.length;
		for (int i = 0; i < length; i++) {
			int switchIndex = Math.abs(random.nextInt()) % (length - i) + i;
			int temp = array[i];
			array[i] = array[switchIndex];
			array[switchIndex] = temp;
		}
		return array;
	}
	
	/**将一个String型的数组转为中间间隔指定字符的字符串，如 a,d,e,f 或者d;we;f;we;a
	 * 
	 * @param array
	 * @return
	 */
	public static String toStringWithCharacter(String[] array, String ch){
		if (array == null) {
			return null;
		}else {
			if (array.length == 1) {
				return array[0];
			}else {
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < array.length-1; i++) {
					stringBuffer.append(array[i]);
					stringBuffer.append(ch);
				}
				stringBuffer.append(array[array.length-1]);
				return stringBuffer.toString();
			}
		}
	}
}
