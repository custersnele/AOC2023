package be.ccfun.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionRecordTest {

	@Test
	public void example1() {
		ConditionRecord record = new ConditionRecord("???.###", new int[]{1,1,3});
		assertEquals(1, record.getArrangements());
	}

	@Test
	public void example2() {
		ConditionRecord record = new ConditionRecord(".??..??...?##.", new int[]{1,1,3});
		assertEquals(4, record.getArrangements());
	}

	@Test
	public void example3() {
		ConditionRecord record = new ConditionRecord("????.#...#..."  , new int[] {4,1,1});
		assertEquals(1, record.getArrangements());
	}

	@Test
	public void example4() {
		ConditionRecord record = new ConditionRecord("?#?#?#?#?#?#?#?", new int[] {1,3,1,6});
		assertEquals(1, record.getArrangements());
	}

	@Test
	public void example5() {
		ConditionRecord record = new ConditionRecord("?###????????", new int[]{ 3,2,1});
		assertEquals(10, record.getArrangements());
	}

	@Test
	public void example6() {
		ConditionRecord record = new ConditionRecord("????.######..#####.", new int[] {1,6,5});
		assertEquals(4, record.getArrangements());
	}

	@Test
	public void example7() {
		ConditionRecord record = new ConditionRecord(".??#????#.", new int[] {3,1});
		assertEquals(3, record.getArrangements());
	}

}
