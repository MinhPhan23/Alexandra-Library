package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class InfoOrganizer implements IInfoOrganizer{
    private final float NAME_COEFFICIENT = 1f;
    private final float AUTHOR_COEFFICIENT = 2f;
    private final float GENRE_COEFFICIENT = 1f;
    @Override
    public ArrayList<Book> sortByTitle(ArrayList<Book> bookList) {
        //bookList.sort();
        return null;
    }

    @Override
    public ArrayList<Book> sortByDate(ArrayList<Book> bookList) {
        return null;
    }

    @Override
    public ArrayList<Book> sortByAuthor(ArrayList<Book> bookList) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByTag(ArrayList<Book> bookList, String[] tags) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByGenre(ArrayList<Book> bookList, String[] genres) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByAuthor(ArrayList<Book> bookList, String[] authors) {
        return null;
    }

    @Override
    public ArrayList<Book> rankBooks(ArrayList<Book> bookList, String[] keywords){
        float[] nameScore = new float[bookList.size()];
        float[] authorScore = new float[bookList.size()];
        float[] tagScore = new float[bookList.size()];
        float[] totalScore = new float[bookList.size()];

        // calculate the score of each book according to the input keywords
        for (int i = 0; i < bookList.size(); i++){
            Book currentBook = bookList.get(i);

            // get the book name, author, and genres
            String[] currentName = currentBook.getName().toLowerCase().split(" ");
            String[] currentAuthor = currentBook.getAuthor().toLowerCase().split(" ");
            ArrayList<String> combinedGenres = new ArrayList<>();
            for (String str : currentBook.getGenres()) {
                String[] words = str.split(" ");
                combinedGenres.addAll(Arrays.asList(words));
            }
            String[] currentGenres = combinedGenres.toArray(new String[0]);

            for(String key : keywords) {
                int matches = 0;
                int length = 0;

                for(String name: currentName) {
                    int[] matchingScore = calculateMatchingScore(name, key.toLowerCase());
                    matches += matchingScore[0];
                    length += matchingScore[1];
                }
                nameScore[i] += length > 0 ? (float)matches/(float)length : 0;

                matches = 0;
                length = 0;
                for(String author: currentAuthor) {
                    int[] matchingScore = calculateMatchingScore(author, key.toLowerCase());
                    matches += matchingScore[0];
                    length += matchingScore[1];
                }
                authorScore[i] += length > 0 ? (float)matches/(float)length : 0;

                matches = 0;
                length = 0;
                for(String tag: currentGenres) {
                    int[] matchingScore = calculateMatchingScore(tag, key.toLowerCase());
                    matches += matchingScore[0];
                    length += matchingScore[1];
                }
                tagScore[i] += length > 0 ? (float)matches/(float)length : 0;
            }

            totalScore[i] = nameScore[i] * NAME_COEFFICIENT
                    + authorScore[i] * AUTHOR_COEFFICIENT
                    + tagScore[i] * GENRE_COEFFICIENT;
        }

        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < totalScore.length; i++) {
            order.add(i);
        }

        Collections.sort(order, (a, b) -> Float.compare(totalScore[b], totalScore[a]));

        // Insert books into a new ArrayList based on the highest score first
        ArrayList<Book> sortedBooks = new ArrayList<>();
        for (int index : order) {
            sortedBooks.add(bookList.get(index));
        }

        return sortedBooks;
    }

    public static int[] calculateMatchingScore(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int matchCount = 0;

        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                matchCount++;
            }
        }

        matchCount = matchCount > 2 ? matchCount : 0;

        return new int[]{matchCount, str1.length()};
    }

}
