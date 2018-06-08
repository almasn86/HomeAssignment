package com.almas.assignment.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.almas.assignment.demo.model.Demo;

@Repository
public class DemoJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Demo> findAll() {
		return jdbcTemplate.query("select * from Demo order by id desc", (rs, rn) -> new Demo(rs.getLong("id"), rs.getString("full_url"),
				rs.getString("short_url"), rs.getString("description")));
	}

	public Demo findById(long id) {
		return jdbcTemplate.queryForObject("select * from Demo where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Demo>(Demo.class));
	}

	public Demo findByShortUrl(String shortUrl) {
		System.out.println(shortUrl);
		return jdbcTemplate.queryForObject(String.format("select * from Demo where short_url = '%s' and rownum=1", shortUrl), 
				(rs, rn) -> new Demo(rs.getLong("id"), rs.getString("full_url"), rs.getString("short_url"), rs.getString("description")));
	}
	
	public int deleteById(long id) {
		return jdbcTemplate.update("delete from Demo where id=?", new Object[] { id });
	}

	public int insert(Demo demo) {
		return jdbcTemplate.update("insert into Demo (id, full_url, short_url, description) " + "values(?, ?, ?, ?)",
				new Object[] { demo.getId(), demo.getFullUrl(), demo.getShortUrl(), demo.getDescription() });
	}

	public int update(Demo demo) {
		return jdbcTemplate.update("update Demo " + " set full_url = ?, short_url = ?, description = ? " + " where id = ?",
				new Object[] { demo.getFullUrl(), demo.getShortUrl(), demo.getDescription(), demo.getId() });
	}

}
