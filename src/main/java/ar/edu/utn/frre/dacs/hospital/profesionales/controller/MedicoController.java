/*
 * Copyright (C) 2015-2019 UTN-FRRe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.edu.utn.frre.dacs.hospital.profesionales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frre.dacs.hospital.profesionales.dao.MedicoRepository;
import ar.edu.utn.frre.dacs.hospital.profesionales.model.Medico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@RestController
@RequestMapping("/api/medico")
@Api("Set of endpoints for Creating and Retrieving Activity Logs")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @ApiOperation(value = "Pagina de Medicos", response = Page.class)
    @GetMapping()
    public Page<Medico> getActivityLogs(Pageable pageable) {
    	return repository.findAll(pageable);
    }
    
}
