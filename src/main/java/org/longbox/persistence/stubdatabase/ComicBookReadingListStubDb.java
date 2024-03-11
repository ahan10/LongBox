package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookListItemReadingDto;
import org.longbox.domainobjects.dto.JsonConvertor;
import org.longbox.persistence.dao.ComicBookReadingListDao;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookReadingList;
import org.longbox.persistence.entity.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

@Getter
@Setter
public class ComicBookReadingListStubDb implements ComicBookReadingListDao, JsonConvertor {

    List<ComicBookListItemReadingDto> records;
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookReadingListStubDb.json";

    @Override
    public void saveToReading(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        List<ComicBookListItemReadingDto> readingList = deserializeStubData(ABSOLUTE_FILE_PATH);
        readingList.add(new ComicBookListItemReadingDto(userId, comicBookId));
        records = readingList;
        serializeStubData();
    }

    @Override
    public void removeFromReading(Long userId, Long comicBookId) {
        List<ComicBookListItemReadingDto> readingList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (int i = 0; i < readingList.size(); i++) {
            if (readingList.get(i).getUserId() == userId && readingList.get(i).getComicBookId() == comicBookId) {
                readingList.remove(i);
            }
        }
        records = readingList;
        serializeStubData();
    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        List<ComicBookListItemReadingDto> readingList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (ComicBookListItemReadingDto record : readingList) {
            if (record.getUserId() == userId && record.getComicBookId() == comicBookId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ComicBookReadingList getReadingComicBook(Long userId, Long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBook> getUsersReadingList(Long userId) {
        return null;
    }

    @Override
    public List<User> getListOfUsersReading(Long comicBookId) {
        return null;
    }

    @Override
    public void serializeStubData() {
        String json = new Gson().toJson(records);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
    }

    @Override
    public List<ComicBookListItemReadingDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<List<ComicBookListItemReadingDto>>(){}.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filepath));
            return new Gson().fromJson(reader, listType);
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
    }
}