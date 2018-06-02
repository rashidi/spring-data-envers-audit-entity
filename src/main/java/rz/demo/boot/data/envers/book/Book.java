package rz.demo.boot.data.envers.book;

import lombok.*;
import rz.demo.boot.data.envers.audit.AuditEnabledEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Rashidi Zin
 */
@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book extends AuditEnabledEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String author;

    @NotBlank
    private String title;

}
