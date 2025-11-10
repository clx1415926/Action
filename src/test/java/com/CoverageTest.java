package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * CoverageTest - 分支条件覆盖测试
 * 目标：覆盖 Main.add() 方法中的所有条件分支
 */
class CoverageTest {

	private Main main = new Main();

	/**
	 * 测试用例1: a < 0, b >= 0
	 * 覆盖条件: a < 0 (第一个分支的第一个条件)
	 */
	@Test
	@DisplayName("测试负数a，非负数b - 应抛出IllegalArgumentException")
	void testNegativeA_NonNegativeB() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-1, 5),
			"当a为负数时应该抛出IllegalArgumentException"
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	/**
	 * 测试用例2: a >= 0, b < 0
	 * 覆盖条件: b < 0 (第一个分支的第二个条件)
	 */
	@Test
	@DisplayName("测试非负数a，负数b - 应抛出IllegalArgumentException")
	void testNonNegativeA_NegativeB() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(5, -1),
			"当b为负数时应该抛出IllegalArgumentException"
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	/**
	 * 测试用例3: a < 0, b < 0
	 * 覆盖条件: a < 0 && b < 0 (第一个分支的两个条件都为真)
	 */
	@Test
	@DisplayName("测试负数a和负数b - 应抛出IllegalArgumentException")
	void testNegativeA_NegativeB() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-5, -3),
			"当a和b都为负数时应该抛出IllegalArgumentException"
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	/**
	 * 测试用例4: a >= 0, b >= 0, 但 Integer.MAX_VALUE - a < b
	 * 覆盖条件: 整数溢出检查 (第二个分支)
	 */
	@Test
	@DisplayName("测试整数溢出 - 应抛出ArithmeticException")
	void testIntegerOverflow() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE, 1),
			"当加法结果溢出时应该抛出ArithmeticException"
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	/**
	 * 测试用例5: a >= 0, b >= 0, 且 Integer.MAX_VALUE - a >= b (边界情况)
	 * 覆盖条件: 刚好不溢出的情况
	 */
	@Test
	@DisplayName("测试边界值 - 刚好不溢出")
	void testBoundaryNoOverflow() {
		int result = main.add(Integer.MAX_VALUE - 1, 1);
		assertEquals(Integer.MAX_VALUE, result, "边界值应该正确计算");
	}

	/**
	 * 测试用例6: a >= 0, b >= 0, 且不溢出 (正常情况)
	 * 覆盖条件: 正常加法路径
	 */
	@Test
	@DisplayName("测试正常加法 - 两个非负数相加")
	void testNormalAddition() {
		int result = main.add(5, 3);
		assertEquals(8, result, "正常加法应该返回正确结果");
	}

	/**
	 * 测试用例7: a = 0, b = 0 (边界情况)
	 * 覆盖条件: 零值情况
	 */
	@Test
	@DisplayName("测试零值加法")
	void testZeroAddition() {
		int result = main.add(0, 0);
		assertEquals(0, result, "零加零应该返回零");
	}

	/**
	 * 测试用例8: a > 0, b = 0
	 * 覆盖条件: 一个为零的情况
	 */
	@Test
	@DisplayName("测试一个数为零的加法")
	void testOneZero() {
		int result = main.add(10, 0);
		assertEquals(10, result, "一个数为零时应该返回另一个数");
	}

	/**
	 * 测试用例9: a = 0, b > 0
	 * 覆盖条件: 另一个为零的情况
	 */
	@Test
	@DisplayName("测试另一个数为零的加法")
	void testOtherZero() {
		int result = main.add(0, 10);
		assertEquals(10, result, "另一个数为零时应该返回第一个数");
	}

	/**
	 * 测试用例10: 大数相加但刚好不溢出
	 * 覆盖条件: 接近边界但不溢出
	 */
	@Test
	@DisplayName("测试大数相加但不溢出")
	void testLargeNumbersNoOverflow() {
		int a = 1000000000;
		int b = 1000000000;
		int result = main.add(a, b);
		assertEquals(2000000000, result, "大数相加应该正确计算");
	}

	/**
	 * 测试用例11: 溢出边界情况 - Integer.MAX_VALUE + 0
	 * 覆盖条件: 最大整数加零
	 */
	@Test
	@DisplayName("测试最大整数加零")
	void testMaxIntPlusZero() {
		int result = main.add(Integer.MAX_VALUE, 0);
		assertEquals(Integer.MAX_VALUE, result, "最大整数加零应该返回最大整数");
	}

	/**
	 * 测试用例12: 溢出边界情况 - 0 + Integer.MAX_VALUE
	 * 覆盖条件: 零加最大整数
	 */
	@Test
	@DisplayName("测试零加最大整数")
	void testZeroPlusMaxInt() {
		int result = main.add(0, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, result, "零加最大整数应该返回最大整数");
	}

	/**
	 * 测试用例13: 溢出情况 - Integer.MAX_VALUE + Integer.MAX_VALUE
	 * 覆盖条件: 两个最大整数相加
	 */
	@Test
	@DisplayName("测试两个最大整数相加 - 应溢出")
	void testMaxIntPlusMaxInt() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE, Integer.MAX_VALUE),
			"两个最大整数相加应该抛出ArithmeticException"
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	/**
	 * 测试用例14: 溢出情况 - 接近边界但溢出
	 * 覆盖条件: Integer.MAX_VALUE - a < b 为真
	 */
	@Test
	@DisplayName("测试接近边界但溢出")
	void testNearBoundaryOverflow() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE - 5, 10),
			"接近边界但溢出应该抛出ArithmeticException"
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}
}
