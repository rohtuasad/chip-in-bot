package ru.rohtuasad.chipin.core.transfer.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;

public interface TransferRepository extends CrudRepository<Transfer, UUID> {
}
