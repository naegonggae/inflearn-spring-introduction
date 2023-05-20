package com.naver.inflearnspring.repository;

import com.naver.inflearnspring.domain.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

// jdbc 템플릿은 순수 jdbc의 커넥션등 생략할 수 있는건 지원해주는데 쿼리는 직접 날려야함
// 실무에서 간간히 쓴다고함
public class JdbcTemplateMemberRepository implements MemberRepository {
	//DI를 아래와 같이 권장함
	private final JdbcTemplate jdbcTemplate;

	@Autowired // 생성자가 하나면 이 어노테아션 생략해도 됨
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Member save(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		// 위 두 단락으로 인설트 문을 만들어줌

		Number key = jdbcInsert.executeAndReturnKey(new
				MapSqlParameterSource(parameters));
		member.setId(key.longValue());
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query("select * from Member where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from Member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("select * from Member", memberRowMapper());
	}

	// 객체 생성에 대한 부분
	private RowMapper<Member> memberRowMapper() {
		// option + enter = 람다형식으로 변경
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));

			return member;
		};
//		return new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member member = new Member();
//				member.setId(rs.getLong("id"));
//				member.setName(rs.getString("name"));
//
//				return member;
//			}
//		};
	}
}
