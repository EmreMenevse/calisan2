package com.calisan2;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.calisan2.entity.Calisan;
import com.calisan2.repository.CalisanRepository;

@SpringBootApplication
public class Calisan2Application {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Calisan2Application.class, args);

//		 Calisan calisan1 = new Calisan();
//		 calisan1.setAd("deneme");
//		 calisan1.setSoyad("deneme");
//		 calisan1.setUnvan("deneme");
//		 calisan1.setSICIL_NO(6666);

		List<Calisan> calisanListesi = new ArrayList<>();
		Calisan calisan2 = new Calisan("deded4", "deded", "liste4", 3232);

		Calisan calisan3 = new Calisan("listeee3", "liste3", "liste3", 1212);

		Connection connection = null;
		CallableStatement callableStatement = null;

		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "Emre");

		// Parametreleri içeren prosedür çağrısı
		String procedureCall = "{call system.mku_calisanekle1(?, ?, ?, ?)}";
		callableStatement = connection.prepareCall(procedureCall);

		// Parametreleri atama
		callableStatement.setString(1, calisan2.getAd());
		callableStatement.setString(2, calisan2.getSoyad());
		callableStatement.setString(3, calisan2.getUnvan());
		callableStatement.setInt(4, calisan2.getSICIL_NO());

		// Prosedürü çağırma
		callableStatement.execute();

	}

}
