package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class MainTest {

	private Main main = new Main();

	// 测试用例 1: 正常情况 - 两个正数相加
	@Test
	@DisplayName("正常情况: 两个正数相加")
	void testNormalAddition() {
		int result = main.add(5, 3);
		assertEquals(8, result);
	}

	// 测试用例 2: 正常情况 - 零值测试
	@Test
	@DisplayName("正常情况: 零值相加")
	void testZeroAddition() {
		int result = main.add(0, 0);
		assertEquals(0, result);
	}

	// 测试用例 3: 正常情况 - 一个数为零
	@Test
	@DisplayName("正常情况: 一个数为零")
	void testOneZero() {
		int result1 = main.add(10, 0);
		assertEquals(10, result1);
		
		int result2 = main.add(0, 20);
		assertEquals(20, result2);
	}

	// 测试用例 4: 正常情况 - 边界值（不溢出）
	@Test
	@DisplayName("正常情况: 边界值不溢出")
	void testBoundaryNoOverflow() {
		int result1 = main.add(Integer.MAX_VALUE, 0);
		assertEquals(Integer.MAX_VALUE, result1);
		
		int result2 = main.add(0, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, result2);
		
		int result3 = main.add(Integer.MAX_VALUE - 1, 1);
		assertEquals(Integer.MAX_VALUE, result3);
	}

	// 测试用例 5: 条件分支覆盖 - a < 0, b >= 0
	@Test
	@DisplayName("异常情况: a < 0, b >= 0")
	void testNegativeA() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-1, 5)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	// 测试用例 6: 条件分支覆盖 - a >= 0, b < 0
	@Test
	@DisplayName("异常情况: a >= 0, b < 0")
	void testNegativeB() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(5, -1)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	// 测试用例 7: 条件分支覆盖 - a < 0, b < 0
	@Test
	@DisplayName("异常情况: a < 0, b < 0")
	void testBothNegative() {
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-1, -2)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}

	// 测试用例 8: 条件分支覆盖 - 溢出情况 (Integer.MAX_VALUE - a < b)
	@Test
	@DisplayName("异常情况: 整数溢出 - MAX_VALUE + 1")
	void testOverflowMaxPlusOne() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE, 1)
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	// 测试用例 9: 条件分支覆盖 - 溢出情况 (a + b > MAX_VALUE)
	@Test
	@DisplayName("异常情况: 整数溢出 - 1 + MAX_VALUE")
	void testOverflowOnePlusMax() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(1, Integer.MAX_VALUE)
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	// 测试用例 10: 条件分支覆盖 - 溢出情况 (两个大数相加)
	@Test
	@DisplayName("异常情况: 整数溢出 - 两个大数相加")
	void testOverflowLargeNumbers() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE / 2 + 1, Integer.MAX_VALUE / 2 + 1)
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	// 测试用例 11: 条件分支覆盖 - 边界溢出 (MAX_VALUE - 1 + 2)
	@Test
	@DisplayName("异常情况: 整数溢出 - 边界值")
	void testOverflowBoundary() {
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE - 1, 2)
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}

	// 测试用例 12: 正常情况 - 大数相加但不溢出
	@Test
	@DisplayName("正常情况: 大数相加但不溢出")
	void testLargeNumbersNoOverflow() {
		int result = main.add(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
		assertEquals(Integer.MAX_VALUE - 1, result);
	}

}
