package com.patrick_laust_ayo.lapayo.services;

import com.patrick_laust_ayo.lapayo.models.ProjectManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Author Patrick
class UserEditorTest {

    @ParameterizedTest
    @CsvSource(value = {"Andy boss|andy0432","Andy Cool Guy|Andy boss", "andy0432|Andy Cool Guy"},
            delimiter = '|')
    void updateProjectmanager(String username, String formerUsername) {

        //Arrange
        UserEditor userEditor = new UserEditor();

        //Act
        ProjectManager projectManager = userEditor.updateProjectmanager(username,formerUsername);

        //Assert
        assertEquals(projectManager.getUsername(), username);
    }
}