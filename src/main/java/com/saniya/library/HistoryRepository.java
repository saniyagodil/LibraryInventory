package com.saniya.library;

import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<HistoryRecord, Long> {
}
