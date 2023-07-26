package ug.co.absa.microsrvc.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;
import ug.co.absa.microsrvc.gateway.domain.Devices;
import ug.co.absa.microsrvc.gateway.repository.DevicesRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DevicesSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Devices}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DevicesResource {

    private final Logger log = LoggerFactory.getLogger(DevicesResource.class);

    private static final String ENTITY_NAME = "devices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DevicesRepository devicesRepository;

    private final DevicesSearchRepository devicesSearchRepository;

    public DevicesResource(DevicesRepository devicesRepository, DevicesSearchRepository devicesSearchRepository) {
        this.devicesRepository = devicesRepository;
        this.devicesSearchRepository = devicesSearchRepository;
    }

    /**
     * {@code POST  /devices} : Create a new devices.
     *
     * @param devices the devices to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new devices, or with status {@code 400 (Bad Request)} if the devices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/devices")
    public Mono<ResponseEntity<Devices>> createDevices(@Valid @RequestBody Devices devices) throws URISyntaxException {
        log.debug("REST request to save Devices : {}", devices);
        if (devices.getId() != null) {
            throw new BadRequestAlertException("A new devices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return devicesRepository
            .save(devices)
            .flatMap(devicesSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/devices/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /devices/:id} : Updates an existing devices.
     *
     * @param id the id of the devices to save.
     * @param devices the devices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated devices,
     * or with status {@code 400 (Bad Request)} if the devices is not valid,
     * or with status {@code 500 (Internal Server Error)} if the devices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/devices/{id}")
    public Mono<ResponseEntity<Devices>> updateDevices(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Devices devices
    ) throws URISyntaxException {
        log.debug("REST request to update Devices : {}, {}", id, devices);
        if (devices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, devices.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return devicesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return devicesRepository
                    .save(devices)
                    .flatMap(devicesSearchRepository::save)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /devices/:id} : Partial updates given fields of an existing devices, field will ignore if it is null
     *
     * @param id the id of the devices to save.
     * @param devices the devices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated devices,
     * or with status {@code 400 (Bad Request)} if the devices is not valid,
     * or with status {@code 404 (Not Found)} if the devices is not found,
     * or with status {@code 500 (Internal Server Error)} if the devices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/devices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Devices>> partialUpdateDevices(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Devices devices
    ) throws URISyntaxException {
        log.debug("REST request to partial update Devices partially : {}, {}", id, devices);
        if (devices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, devices.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return devicesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Devices> result = devicesRepository
                    .findById(devices.getId())
                    .map(existingDevices -> {
                        if (devices.getAbsaTranRef() != null) {
                            existingDevices.setAbsaTranRef(devices.getAbsaTranRef());
                        }
                        if (devices.getRecordId() != null) {
                            existingDevices.setRecordId(devices.getRecordId());
                        }
                        if (devices.getDeviceId() != null) {
                            existingDevices.setDeviceId(devices.getDeviceId());
                        }
                        if (devices.getDeviceName() != null) {
                            existingDevices.setDeviceName(devices.getDeviceName());
                        }
                        if (devices.getDeviceType() != null) {
                            existingDevices.setDeviceType(devices.getDeviceType());
                        }
                        if (devices.getDeviceDescription() != null) {
                            existingDevices.setDeviceDescription(devices.getDeviceDescription());
                        }
                        if (devices.getTimestamp() != null) {
                            existingDevices.setTimestamp(devices.getTimestamp());
                        }
                        if (devices.getDelflg() != null) {
                            existingDevices.setDelflg(devices.getDelflg());
                        }
                        if (devices.getRecordStatus() != null) {
                            existingDevices.setRecordStatus(devices.getRecordStatus());
                        }
                        if (devices.getFreeField() != null) {
                            existingDevices.setFreeField(devices.getFreeField());
                        }
                        if (devices.getFreeField1() != null) {
                            existingDevices.setFreeField1(devices.getFreeField1());
                        }
                        if (devices.getFreeField2() != null) {
                            existingDevices.setFreeField2(devices.getFreeField2());
                        }
                        if (devices.getFreeField3() != null) {
                            existingDevices.setFreeField3(devices.getFreeField3());
                        }
                        if (devices.getFreeField4() != null) {
                            existingDevices.setFreeField4(devices.getFreeField4());
                        }
                        if (devices.getFreeField5() != null) {
                            existingDevices.setFreeField5(devices.getFreeField5());
                        }
                        if (devices.getFreeField6() != null) {
                            existingDevices.setFreeField6(devices.getFreeField6());
                        }
                        if (devices.getFreeField7() != null) {
                            existingDevices.setFreeField7(devices.getFreeField7());
                        }
                        if (devices.getFreeField8() != null) {
                            existingDevices.setFreeField8(devices.getFreeField8());
                        }
                        if (devices.getFreeField9() != null) {
                            existingDevices.setFreeField9(devices.getFreeField9());
                        }
                        if (devices.getFreeField10() != null) {
                            existingDevices.setFreeField10(devices.getFreeField10());
                        }
                        if (devices.getFreeField11() != null) {
                            existingDevices.setFreeField11(devices.getFreeField11());
                        }
                        if (devices.getFreeField12() != null) {
                            existingDevices.setFreeField12(devices.getFreeField12());
                        }
                        if (devices.getFreeField13() != null) {
                            existingDevices.setFreeField13(devices.getFreeField13());
                        }
                        if (devices.getFreeField14() != null) {
                            existingDevices.setFreeField14(devices.getFreeField14());
                        }
                        if (devices.getFreeField15() != null) {
                            existingDevices.setFreeField15(devices.getFreeField15());
                        }
                        if (devices.getFreeField15ContentType() != null) {
                            existingDevices.setFreeField15ContentType(devices.getFreeField15ContentType());
                        }
                        if (devices.getFreeField16() != null) {
                            existingDevices.setFreeField16(devices.getFreeField16());
                        }
                        if (devices.getFreeField17() != null) {
                            existingDevices.setFreeField17(devices.getFreeField17());
                        }
                        if (devices.getFreeField18() != null) {
                            existingDevices.setFreeField18(devices.getFreeField18());
                        }
                        if (devices.getFreeField18ContentType() != null) {
                            existingDevices.setFreeField18ContentType(devices.getFreeField18ContentType());
                        }
                        if (devices.getFreeField19() != null) {
                            existingDevices.setFreeField19(devices.getFreeField19());
                        }
                        if (devices.getCreatedAt() != null) {
                            existingDevices.setCreatedAt(devices.getCreatedAt());
                        }
                        if (devices.getCreatedBy() != null) {
                            existingDevices.setCreatedBy(devices.getCreatedBy());
                        }
                        if (devices.getUpdatedAt() != null) {
                            existingDevices.setUpdatedAt(devices.getUpdatedAt());
                        }
                        if (devices.getUpdatedBy() != null) {
                            existingDevices.setUpdatedBy(devices.getUpdatedBy());
                        }

                        return existingDevices;
                    })
                    .flatMap(devicesRepository::save)
                    .flatMap(savedDevices -> {
                        devicesSearchRepository.save(savedDevices);

                        return Mono.just(savedDevices);
                    });

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /devices} : get all the devices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of devices in body.
     */
    @GetMapping("/devices")
    public Mono<List<Devices>> getAllDevices() {
        log.debug("REST request to get all Devices");
        return devicesRepository.findAll().collectList();
    }

    /**
     * {@code GET  /devices} : get all the devices as a stream.
     * @return the {@link Flux} of devices.
     */
    @GetMapping(value = "/devices", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Devices> getAllDevicesAsStream() {
        log.debug("REST request to get all Devices as a stream");
        return devicesRepository.findAll();
    }

    /**
     * {@code GET  /devices/:id} : get the "id" devices.
     *
     * @param id the id of the devices to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the devices, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/devices/{id}")
    public Mono<ResponseEntity<Devices>> getDevices(@PathVariable Long id) {
        log.debug("REST request to get Devices : {}", id);
        Mono<Devices> devices = devicesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(devices);
    }

    /**
     * {@code DELETE  /devices/:id} : delete the "id" devices.
     *
     * @param id the id of the devices to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/devices/{id}")
    public Mono<ResponseEntity<Void>> deleteDevices(@PathVariable Long id) {
        log.debug("REST request to delete Devices : {}", id);
        return devicesRepository
            .deleteById(id)
            .then(devicesSearchRepository.deleteById(id))
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }

    /**
     * {@code SEARCH  /_search/devices?query=:query} : search for the devices corresponding
     * to the query.
     *
     * @param query the query of the devices search.
     * @return the result of the search.
     */
    @GetMapping("/_search/devices")
    public Mono<List<Devices>> searchDevices(@RequestParam String query) {
        log.debug("REST request to search Devices for query {}", query);
        return devicesSearchRepository.search(query).collectList();
    }
}
