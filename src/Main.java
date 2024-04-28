public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

// passes 2/3rds
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length() != p.length()){
            return false;
        }

        char cur = ' ';

        for(int i = 0; i < s.length(); i++){
            if(p.charAt(i) == '.'){
                continue;
            }
            if(p.charAt(i) == '*'){
                cur = p.charAt(i-1);
            }

            if(s.charAt(i) == p.charAt(i) || cur == s.charAt(i) || cur == '.'){
                continue;
            }

            return false;
        }
        return true;
    }
}
/*
example
aab
c*a*b*
this should return true

this states that any content preceding and including the * can be discarded (matching 0) like with c* or it can match
more than 0 such as a* = aa with 1 match


 */

class Solution {
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();  //this is base case where if the pattern is empty, then return is text is
        // also empty. for true, both text and pattern should be exhausted. Ultimately with the above case we get here after last recusive
        // case before base case passes 'empty' and 'b*' where b* is eliminated in first function passing two empty strings.
        // since pattern is empty and so is text, this becomes true

        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        // if text isn't empty, check is index 0 char same for t and p

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){ // if pattern greater than 2 in length and second char is '*'
            return (isMatch(text, pattern.substring(2))
                    // need this so that if there is no matching first char, then can just discard the preceding characters in the pattern
                    || (first_match && isMatch(text.substring(1), pattern)) );
            // need this line where if there is a matching character such as with aab and a*b*, we cannot discard 'a*' until
            // we know we are done using the * power to make it as many "a's" as needed. So we knock of the first a of text
            // and run it again with as 'ab' and 'a*b*', then run this funtion again knocking off the second 'a' so 'ab' and
            // 'a*b*' just becomes 'b' and 'a*b*'. then we feed this into recursive case again only then can this execute
            // for the non-boolean flag recursive case where we can eliminate 'a*' from 'a*b*' since we no longer are matching first
            // char. since substring at index 1 where length of text 'b' is just 1, it won't throw an error as defined by substring
            //function even though 1 is technically an OOB index.
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1)); // will execute if
            // no '*'. this line would run true if two identical strings
        }
    }
}