package newspaper;

import java.util.List;
import java.util.Objects;

public abstract class Article implements Comparable<Article>{
    private String author;
    private Header header;
    private List<String> paragraphs;

    public Article(String author, Header header, List<String> paragraphs) {
        this.author = author;
        this.header = header;
        this.paragraphs = paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return header.equals(article.header) && paragraphs.equals(article.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, paragraphs);
    }

    @Override
    public int compareTo(Article o) {
        return o.getImportance().compareTo(getImportance());
    }

    public String getAuthor() {
        return author;
    }

    public Header getHeader() {
        return header;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public abstract Integer getImportance();
}
