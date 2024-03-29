package com.vemser.dbc.searchorganic.repository;

import com.vemser.dbc.searchorganic.model.Empresa;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query(
            value = "SELECT e.* FROM EMPRESA e INNER JOIN PRODUTO p ON e.id_empresa = p.id_empresa",
            countQuery = "SELECT COUNT(DISTINCT e.id_empresa) FROM EMPRESA e INNER JOIN PRODUTO p ON e.id_empresa = p.id_empresa",
            nativeQuery = true)
    Page<Empresa> findAllWithProdutos(Pageable pageable);

    @Query(value = "SELECT e.*, p.* FROM EMPRESA e LEFT JOIN PRODUTO p ON e.id_empresa = p.id_empresa WHERE e.id_empresa = :idEmpresa", nativeQuery = true)
    Optional<Empresa> findByIdWithProdutos(@Param("idEmpresa") Integer idEmpresa);


    @Query(value = "SELECT COUNT(*) FROM USUARIO_CARGO uc2 \n" +
            "WHERE uc2.ID_USUARIO = :userId AND uc2.ID_CARGO = 1", nativeQuery = true)
    Integer existsAdminCargoByUserId(@Param("userId") Integer userId);


    @Query(value = "SELECT COUNT(*) FROM USUARIO_CARGO uc2 \n" +
            "WHERE uc2.ID_USUARIO = :userId AND uc2.ID_CARGO = 2", nativeQuery = true)
    Integer existsEmpresaCargoByUserId(@Param("userId") Integer userId);


}
