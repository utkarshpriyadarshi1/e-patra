package in.updev.repository;

import in.updev.edastavej.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f FROM File f WHERE " +
            "(:fileName IS NULL OR f.fileName LIKE %:fileName%) AND " +
            "(:description IS NULL OR f.description LIKE %:description%) AND " +
            "(:categoryId IS NULL OR f.category.id = :categoryId) AND " +
            "(:subCategoryId IS NULL OR f.subCategory.id = :subCategoryId) AND " +
            "(:startDate IS NULL OR f.uploadDate >= :startDate) AND " +
            "(:endDate IS NULL OR f.uploadDate <= :endDate)")
    List<File> searchFiles(@Param("fileName") String fileName,
                           @Param("description") String description,
                           @Param("categoryId") Long categoryId,
                           @Param("subCategoryId") Long subCategoryId,
                           @Param("startDate") String startDate,
                           @Param("endDate") String endDate);
}