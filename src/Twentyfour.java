import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Twentyfour {

	public static void main(String[] args) {
		// StringBuilder expression = new StringBuilder();
		// tryTwentyfour(15, 2, 3, 4, expression);
		// System.out.println("expression:" + expression);
		//
		// System.out.println("i/36" + ",i%36/6" + ",i%6:");
		// for (int i = 0; i < 6 * 6 * 6; i++) {
		// if (i == 0) {
		// System.out.println(i+"==> " + (0 / 36) + " , 0 " + ", " + 0 % 6);
		// } else {
		// System.out.println(i+"==> " + (i / 36) + " , " + ( i % 36 / 6) + " ,
		// " + i % 6);
		// }
		// }
		List<String> allExpressions = tryAllTwentyfour(1, 2, 3, 4);
		// Collections.sort(allExpressions);
		String all = "";
		for (String s : allExpressions) {
			all = String.format("%s %n %s", all, s);
		}
		System.out.println("all:" + all);
	}

	// һ�������ʽ
	private static boolean tryTwentyfour(int a, int b, int c, int d, StringBuilder expression) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(a);
		numbers.add(b);
		numbers.add(c);
		numbers.add(d);
		List<ArrayList<Integer>> groups = GetNumbersGroup(numbers);
		for (ArrayList<Integer> group : groups) {
			int[] numberArray = new int[group.size()];
			int p = 0;
			for (int i : group) {
				numberArray[p] = i;
				p++;
			}
			if (tryEach(numberArray[p - 4], numberArray[p - 3], numberArray[p - 2], numberArray[p - 1], expression))
				return true;
		}

		
		return false;
	}

	// ���м�����ʽ
	private static List<String> tryAllTwentyfour(int a, int b, int c, int d) {
		List<String> allExpressions = new ArrayList<String>();

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(a);
		numbers.add(b);
		numbers.add(c);
		numbers.add(d);
		List<ArrayList<Integer>> groups = GetNumbersGroup(numbers);
		for (ArrayList<Integer> group : groups) {
			int[] numberArray = new int[group.size()];
			int p = 0;
			for (int i : group) {
				numberArray[p] = i;
				p++;
			}
			// allExpressions.add(String.format("%n%s %s %s %s", numberArray[p -
			// 4], numberArray[p - 3],
			// numberArray[p - 2], numberArray[p - 1]));
			List<String> eachExpression = tryAllEach(numberArray[p - 4], numberArray[p - 3], numberArray[p - 2],
					numberArray[p - 1]);
			// ����ȫ���ǡ�+������'*'������ı��ʽ
			boolean iscontainplus = ContainsCharInExpressGroups('+', allExpressions);
			boolean iscontaintimes = ContainsCharInExpressGroups('*', allExpressions);
			List<String> neweachExpression = new ArrayList<String>();
			if (iscontainplus || iscontaintimes) {
				for (String s : eachExpression) {
					if (iscontainplus && GetStringIndexCount(s, '+') < 3) {
						neweachExpression.add(s);
					}
					if (iscontaintimes && GetStringIndexCount(s, '*') < 3) {
						neweachExpression.add(s);
					}
				}
				eachExpression = neweachExpression;
			}

			List<String> neweachExpression1 = new ArrayList<String>();
			for (String s : eachExpression) {
				if (!allExpressions.contains(s)) {
					neweachExpression1.add(s);
				}
			}

			allExpressions.addAll(neweachExpression1);

		}

		return allExpressions;

	}

	// ����ָ�����ʽ�б����Ƿ���2�����ϵ�ָ���ַ�
	private static boolean ContainsCharInExpressGroups(char compareChar, List<String> eachExpressions) {
		for (String s : eachExpressions) {
			if (GetStringIndexCount(s, compareChar) > 2) {
				return true;
			}
		}
		return false;
	}

	// ��ȡһ�������е��������
	private static List<ArrayList<Integer>> GetNumbersGroup(ArrayList<Integer> numbers) {
		List<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
		int[] b = new int[numbers.size()];

		for (int i = 0; i < numbers.size(); i++) {
			b[0] = numbers.get(i);
			for (int j = 0; j < numbers.size(); j++) {
				b[1] = numbers.get(j);
				for (int k = 0; k < numbers.size(); k++) {
					b[2] = numbers.get(k);
					for (int m = 0; m < numbers.size(); m++) {
						b[3] = numbers.get(m);

						if (i != j && i != k && i != m && j != k && j != m & k != m) {
							ArrayList<Integer> group = new ArrayList<Integer>();
							for (int p = 0; p < b.length; p++) {
								group.add(b[p]);
							}
							groups.add(group);
						}

					}
				}

			}

		}
		return groups;
	}

	// ����һ����ʽ��ϳɹ�������
	private static boolean tryEach(int a, int b, int c, int d, StringBuilder expression) {
		for (int i = 0; i < 6 * 6 * 6; i++) {
			// 1������˳��a��b���ٺ�c���ٺ�d
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(a, b, i / 36, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(temp1, c, i % 36 / 6, expression1, "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp2, d, i % 6, expression2, "", outExpression3);
				if (result == 24) {
					expression.append(outExpression3.toString());
					return true;
				}
			}
			// 2������˳��a��b��c��d��ǰ�沿�ֺͺ��沿��
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(a, b, i / 36, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(c, d, i % 6, "", "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp1, temp2, i % 36 / 6, expression1, expression2, outExpression3);
				if (result == 24) {
					expression.append(outExpression3.toString());
					return true;
				}

			}
			// 3������˳��b��c���ٺ�a������d����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(b, c, i % 36 / 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(a, temp1, i / 36, "", expression1, outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp2, d, i % 36, expression2, "", outExpression3);
				if (result == 24) {
					expression.append(outExpression3.toString());
					return true;
				}
			}

			// 4������˳��b��c������d������a����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(b, c, i % 36 / 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(temp1, d, i % 6, expression1, "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(a, temp2, i / 36, "", expression2, outExpression3);
				if (result == 24) {
					expression.append(outExpression3.toString());
					return true;
				}
			}

			// 5������˳��c��d���ٺ�b���ٺ�a����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(c, d, i % 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(b, temp1, i % 36 / 6, "", expression1, outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(a, temp2, i / 36, "", expression2, outExpression3);
				if (result == 24) {
					expression.append(outExpression3.toString());
					return true;
				}
			}
		}
		expression.append("Abandoned");
		return false;
	}

	// ������������ı��ʽ��ϲ�����
	private static List<String> tryAllEach(int a, int b, int c, int d) {
		List<String> expressions = new ArrayList<String>();
		for (int i = 0; i < 6 * 6 * 6; i++) {
			// 1������˳��a��b���ٺ�c���ٺ�d
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(a, b, i / 36, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(temp1, c, i % 36 / 6, expression1, "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp2, d, i % 6, expression2, "", outExpression3);
				if (result == 24) {
					if (!expressions.contains(outExpression3.toString())) {
						expressions.add(outExpression3.toString());
					}
				}
			}
			// 2������˳��a��b��c��d��ǰ�沿�ֺͺ��沿��
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(a, b, i / 36, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(c, d, i % 6, "", "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp1, temp2, i % 36 / 6, expression1, expression2, outExpression3);
				if (result == 24) {
					if (!expressions.contains(outExpression3.toString())) {
						expressions.add(outExpression3.toString());
					}
				}

			}
			// 3������˳��b��c���ٺ�a������d����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(b, c, i % 36 / 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(a, temp1, i / 36, "", expression1, outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(temp2, d, i % 36, expression2, "", outExpression3);
				if (result == 24) {
					if (!expressions.contains(outExpression3.toString())) {
						expressions.add(outExpression3.toString());
					}
				}
			}

			// 4������˳��b��c������d������a����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(b, c, i % 36 / 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(temp1, d, i % 6, expression1, "", outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(a, temp2, i / 36, "", expression2, outExpression3);
				if (result == 24) {
					if (!expressions.contains(outExpression3.toString())) {
						expressions.add(outExpression3.toString());
					}
				}
			}

			// 5������˳��c��d���ٺ�b���ٺ�a����
			{
				StringBuilder outExpression1 = new StringBuilder();
				int temp1 = ResultOf(c, d, i % 6, "", "", outExpression1);
				String expression1 = outExpression1.toString();

				StringBuilder outExpression2 = new StringBuilder();
				int temp2 = ResultOf(b, temp1, i % 36 / 6, "", expression1, outExpression2);
				String expression2 = outExpression2.toString();

				StringBuilder outExpression3 = new StringBuilder();
				int result = ResultOf(a, temp2, i / 36, "", expression2, outExpression3);
				if (result == 24) {
					if (!expressions.contains(outExpression3.toString())) {
						expressions.add(outExpression3.toString());
					}
				}
			}
		}
		if (expressions.size() <= 0) {
			expressions.add("NA");
		}
		return expressions;
	}

	// ��������
	private static int ResultOf(int x, int y, int method, String expressionLeft, String expressionRight,
			StringBuilder expression) {
		if (expressionLeft == "Abandoned" || expressionRight == "Abandoned" || (x == 0 && method == 5)
				|| (y == 0 && method == 4)) {
			expression.append("Abandoned");
			return -1;
		}

		int result = 0;

		expressionLeft = expressionLeft == "" ? Integer.toString(x) : expressionLeft;
		expressionRight = expressionRight == "" ? Integer.toString(y) : expressionRight;

		switch (method) {
		case 0: // ��
			result = x + y;
			expression.append(String.format("%s+%s", expressionLeft, expressionRight));
			break;
		case 1:// ��
			result = x - y;
			expression.append(String.format("%s-%s", expressionLeft, expressionRight));
			break;
		case 2:// ����
			result = y - x;
			expression.append(String.format("%s-%s", expressionRight, expressionLeft));
			break;
		case 3:// ����
			result = x * y;
			boolean isAll = false;
			if (GetStringIndexCount(expressionLeft, '*') > 0 || GetStringIndexCount(expressionRight, '*') > 0) {
				isAll = true;
			}
			String newExpression = String.format("%s*%s",
					expressionLeft.length() > 1 ? String.format("(%s)", expressionLeft) : expressionLeft,
					expressionRight.length() > 1 ? String.format("(%s)", expressionRight) : expressionRight);
			if (isAll) {
				newExpression = newExpression.replace("(", "");
				newExpression = newExpression.replace(")", "");
			}
			expression.append(newExpression);

			break;
		case 4:// ����
			if (x % y == 0) {
				result = x / y;
				expression.append(String.format("%s/%s",
						expressionLeft.length() > 1 ? String.format("(%s)", expressionLeft) : expressionLeft,
						expressionRight.length() > 1 ? String.format("(%s)", expressionRight) : expressionRight));
			} else {
				expression.append("Abandoned");
			}
			break;
		case 5:// ��
			if (y % x == 0) {
				result = y / x;
				expression.append(String.format("%s/%s",
						expressionRight.length() > 1 ? String.format("(%s)", expressionRight) : expressionRight,
						expressionLeft.length() > 1 ? String.format("(%s)", expressionLeft) : expressionLeft));
			} else {
				expression.append("Abandoned");
			}
			break;
		}
		// ���㲻�Ϸ����򷵻�-1�����ʽΪAbandoned��
		if (expression.toString() == "Abandoned") {
			return -1;
		}
		return result;
	}

	// ��ȡһ���ַ�����ĳһ�ַ����ֵĴ���
	private static int GetStringIndexCount(String compareStr, char compareChar) {
		int cnt = 0;
		int offset = 0;
		while ((offset = compareStr.indexOf(compareChar, offset)) != -1) {
			offset = offset + 1;
			cnt++;
		}
		return cnt;
	}

}
