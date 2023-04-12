package efids.aftab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import efids.aftab.model.AftabArrival;

@Repository
public interface AftabArrivalRepository extends JpaRepository<AftabArrival, String> {
	
	@Query(value = "WITH\r\n" + 
			"V_START_DATE AS\r\n" + 
			"( SELECT TO_CHAR( SYSDATE - INTERVAL '2' DAY   ,'YYYYMMDD' ) || '170000' AS V_START_DATE FROM DUAL )\r\n" + 
			", V_END_DATE AS\r\n" + 
			"( SELECT TO_CHAR( SYSDATE + INTERVAL '1' DAY   ,'YYYYMMDD' ) || '165999' AS V_END_DATE FROM DUAL )\r\n" + 
			"SELECT URNO\r\n" + 
			"    , ADID\r\n" + 
			"    , ORG3\r\n" + 
			"    , FLNO\r\n" + 
			"    , FLTI\r\n" + 
			"    , FTYP\r\n" + 
			"    , BLT1\r\n" + 
			"    , BLT2\r\n" + 
			"    , GTA1\r\n" + 
			"    , GTA2\r\n" + 
			"    , HOPO\r\n" + 
			"     , REMP\r\n" + 
			"    , CASE WHEN UPPER(TTYP) = 'NULL' THEN '' ELSE TTYP END AS TTYP\r\n" + 
			"    , REGEXP_REPLACE(SUBSTR(VIAL, 1, 4) , '[^0-9A-Za-z()./%,:;#&-/+ ]', ' ') AS VIAL\r\n" + 
			"    , STOA\r\n" + 
			"\r\n" + 
			"FROM CEDAAODB.AFTTAB\r\n" + 
			"WHERE HOPO = 'BKK' AND ADID = 'A' AND STOA BETWEEN  ( SELECT V_START_DATE FROM V_START_DATE ) AND ( SELECT V_END_DATE FROM V_END_DATE )", nativeQuery = true)
	List<AftabArrival> tableArrival();
}
