/** Palindrome class. */
public class Palindrome {
    /** Convert a string to a deque data structure. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> resDeque = new LinkedListDeque<>();
        for (char character : word.toCharArray()) {
            resDeque.addLast(character);
        }
        return resDeque;
    }

    /** Return true if a word is palindrome, otherwise false. */
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    /** Helper method for isPalindrome. */
    private boolean isPalindromeHelper(Deque<Character> word) {
        // Base case
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        // Recursion rule
        Character firstWord = word.removeFirst();
        Character lastWord = word.removeLast();
        return (firstWord.equals(lastWord) && isPalindromeHelper(word));
    }

    /** OffByOne version of isPalindrome method. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }

    /** Helper method for OffByOne version of isPalindrome. */
    private boolean isPalindromeHelper(Deque<Character> word, CharacterComparator cc) {
        // Base case
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        // Recursion rule
        char firstWord = word.removeFirst();
        char lastWord = word.removeLast();
        return (cc.equalChars(firstWord, lastWord) && isPalindromeHelper(word, cc));
    }

}
