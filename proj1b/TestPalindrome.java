import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {

        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome(null));

        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));

    }

    @Test
    public void testisGeneralizedPalindrome() {

        assertTrue(palindrome.isPalindrome("",offByOne));
        assertTrue(palindrome.isPalindrome("a",offByOne));
        assertFalse(palindrome.isPalindrome(null,offByOne));

        assertFalse(palindrome.isPalindrome("cat",null));
        assertTrue(palindrome.isPalindrome("racecar",null));

        assertTrue(palindrome.isPalindrome("flake",offByOne));
        assertFalse(palindrome.isPalindrome("abcde",offByOne));
        assertFalse(palindrome.isPalindrome("racecar",offByOne));
    }
}