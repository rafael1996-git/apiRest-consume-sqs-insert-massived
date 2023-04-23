package com.huellitas.serverless.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huellazteca.core.OracleDBPool;
import com.huellitas.serverless.models.domain.Pet;
import com.huellitas.serverless.security.Credentials;
import com.huellitas.serverless.utilities.ConstantesSQL;
import com.huellitas.serverless.utilities.Constants;
import com.huellitas.serverless.utilities.HuellAztException;

import oracle.jdbc.OracleTypes;

public class Repository {

	private static final Logger log = LoggerFactory.getLogger(Repository.class);

	static {
		try {
			if (Credentials.DB_CONFIG == null) {
				throw new HuellAztException(Constants.FAILED_GET_DB_CONFIG, Constants.CODIGO_FAILED_GET_DB_CONFIG);
			}

			OracleDBPool.initSingletonConnectionCredentials(Credentials.DB_CONFIG.getUrl(),
					Credentials.DB_CONFIG.getUser(), Credentials.DB_CONFIG.getPass());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void SetLoad(Pet data) throws Exception {
		CallableStatement cs = null;
		Connection conn=null;
		int cont = 1;
		try {
			 conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_REFUGIO_REGISTRATION_MASIVE);
			 //conn.setAutoCommit(false);
			 	cs = conn.prepareCall(ConstantesSQL.INSERT_REFUGIO_REGISTRATION_MASIVE);
				cs.registerOutParameter(1, OracleTypes.NUMBER);

				cs.setString(2, data.getName());
				cs.setString(3, data.getRescueDate());
				cs.setInt(4, data.getIdPetType());
				cs.setInt(5, data.getIdSize());
				cs.setInt(6, data.getIdGender());
				cs.setString(7, data.getAge());
				cs.setInt(8, data.getSterilized());
				cs.setInt(9, data.getWormed());
				cs.setInt(10, data.getVaccinated());
				cs.setInt(11, data.getIdDisability());
				cs.setInt(12, data.getPrice());
				cs.setString(13, data.getObservations());
				cs.setInt(14, 0);
				cs.setInt(15, 1);
				cs.setInt(16, 1);
				cs.setInt(17,data.getIdUser());
				cs.executeUpdate();
			 	for(Integer i:data.getVaccines()) {
					saveVaccine(cs.getLong(1),i);
				}
				for(Integer itera:data.getFeatures()) {
					saveFeature(cs.getLong(1), itera);
				}
				
			 conn.commit();
				cs.close();
		}catch (Exception e){
			log.info("ERROR: ",e);
			e.printStackTrace();
			System.out.println(new StringBuilder("Repository.SetLoad , ").append(e.getMessage()));
			throw new Exception(e.getMessage());
		} finally {
			if (cs != null) { try { cs.close(); } catch (Exception e) {} }
		}
	}
	
    public void saveVaccine(Long idPet, Integer idVaccine) throws Exception {
        CallableStatement cs = null;
        try {
            Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_VACCINE_PET);
            cs = conn.prepareCall(ConstantesSQL.INSERT_VACCINE_PET);
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setLong(2,idPet);
            cs.setInt(3,idVaccine);
            cs.execute();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error -> PetDaoImpl.saveVaccine: " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            if (cs != null) { try { cs.close(); } catch (Exception e) {} }
        }
    }

    public void saveFeature(Long idPet, Integer idVaccine) throws Exception {
        CallableStatement cs = null;
        try {
            Connection conn = OracleDBPool.getSingletonConnection(ConstantesSQL.DB_TIMEOUT, ConstantesSQL.INSERT_FEATURE);
            cs = conn.prepareCall(ConstantesSQL.INSERT_FEATURE);
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setLong(2,idPet);
            cs.setInt(3,idVaccine);
            cs.execute();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error -> PetDaoImpl.saveFeature: " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            if (cs != null) { try { cs.close(); } catch (Exception e) {} }
        }
    }

}
