package bg.softuni.automappingobjectsexercise.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static bg.softuni.automappingobjectsexercise.enums.Commands.VALIDATION_TITLE;
import static bg.softuni.automappingobjectsexercise.enums.Commands.VALIDATION_TRAILER;

public class EditGameDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void updateFields(Map<String, String> newValues) {
        Set<String> keys = newValues.keySet();
        for (String key : keys) {
            String value = newValues.get(key);
            switch (key) {
                case "title":
                    if (!Pattern.matches(VALIDATION_TITLE, value)) {
                        throw new IllegalArgumentException("Title is not valid!");
                    }

                    setTitle(value);
                    break;

                case "price":
                    if (new BigDecimal(value).signum() != 1) {
                        throw new IllegalArgumentException("Price is not valid!");
                    }

                    setPrice(new BigDecimal(value));
                    break;

                case "size":
                    if (Double.parseDouble(value) <= 0) {
                        throw new IllegalArgumentException("Size is not valid!");
                    }

                    setSize(Double.parseDouble(value));
                    break;

                case "trailer":
                    if (!Pattern.matches(VALIDATION_TRAILER, value)) {
                        throw new IllegalArgumentException("Trailer is not valid!");
                    }

                    setTrailer(value);
                    break;

                case "imageThumbnail":
                    if (!(imageThumbnail.startsWith("http://") || imageThumbnail.startsWith("https://"))) {
                        throw new IllegalArgumentException("Thumbnail URL is not valid!");
                    }

                    setImageThumbnail(value);
                    break;

                case "description":
                    if (description.length() < 20) {
                        throw new IllegalArgumentException("Description is not valid!");
                    }

                    setDescription(value);
                    break;

                case "releaseDate":
                    setReleaseDate(LocalDate.parse(value));
                    break;
            }
        }
    }
}
