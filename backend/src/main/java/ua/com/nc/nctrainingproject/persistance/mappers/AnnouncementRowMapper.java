package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AnnouncementQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementRowMapper implements RowMapper<Announcement> {
	@Override
	public Announcement mapRow(ResultSet resultSet, int i) throws SQLException {
		Announcement announcement = new Announcement();

		announcement.setDescription(resultSet.getString(AnnouncementQuery.DESCRIPTION));
		announcement.setAnnouncementDate(resultSet.getDate(AnnouncementQuery.ANNOUNCEMENT_DATE));
		announcement.setBookID(resultSet.getInt(AnnouncementQuery.BOOK_ID));
		announcement.setPriority(resultSet.getString(AnnouncementQuery.PRIORITY));
		announcement.setOwnerId(resultSet.getInt(AnnouncementQuery.OWNER_ID));
		announcement.setStatus(resultSet.getString(AnnouncementQuery.STATUS));
		return announcement;
	}
}
