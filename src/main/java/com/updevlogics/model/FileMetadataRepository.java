package com.updevlogics.model;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
    Optional<FileMetadata> findByHash(String hash); // Check for duplicates
}
