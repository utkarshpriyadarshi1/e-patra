package in.updev.repository;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
public class Category {
}
