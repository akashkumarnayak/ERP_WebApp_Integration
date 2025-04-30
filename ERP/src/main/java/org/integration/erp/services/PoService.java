package org.integration.erp.services;

import org.integration.erp.entities.Organization;
import org.integration.erp.entities.PurchaseOrder;
import org.integration.erp.entities.ReleaseStatus;
import org.integration.erp.exceptions.PurchaseOrderAlreadyReleasedException;
import org.integration.erp.exceptions.PurchaseOrderNotExistException;
import org.integration.erp.producers.IProducer;
import org.integration.erp.producers.KafkaProducer;
import org.integration.erp.repositories.PoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoService {

    @Autowired
    private PoRepository poRepository;

    @Value("${spring.kafka.producer.po.310.topic}")
    private String poProducerTopic;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private IProducer<PurchaseOrder> kafkaProducer;

    public PurchaseOrder updatePoStatus(PurchaseOrder po) throws PurchaseOrderAlreadyReleasedException
    {
        Optional<PurchaseOrder> DBstoredPoOptional = poRepository.findBypoNumber(po.getPoNumber());

        if(DBstoredPoOptional.isPresent()) {
            PurchaseOrder DBstoredPo = DBstoredPoOptional.get();

            if(DBstoredPo.getStatus() == ReleaseStatus.RELEASED)
            {
                throw new PurchaseOrderAlreadyReleasedException( String.format("Purchase order %s is already in released status",DBstoredPo.getStatus()));
            }

            DBstoredPo.setStatus(po.getStatus());
            return poRepository.save(DBstoredPo);
        }
        else
        {
            throw new PurchaseOrderNotExistException(String.format("Purchase order %s does not exist",po.getPoNumber()));
        }

    }

    public PurchaseOrder createAndSavePo(PurchaseOrder po)
    {
        Organization org = organizationService.checkOrganization(po.getOrg());

        if(org!=null)
            po.setOrg(org);

        PurchaseOrder savedPo = poRepository.save(po);
        kafkaProducer.sendMessage(poProducerTopic, savedPo);
        return savedPo;
    }
}
