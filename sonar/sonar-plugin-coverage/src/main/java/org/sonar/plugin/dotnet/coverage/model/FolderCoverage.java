/**
 * Maven and Sonar plugin for .Net
 * Copyright (C) 2010 Jose Chillan and Alexandre Victoor
 * mailto: jose.chillan@codehaus.org or alexvictoor@codehaus.org
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

/*
 * Created on May 14, 2009
 */
package org.sonar.plugin.dotnet.coverage.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the code coverage for a folder.
 * 
 * @author Alexandre Victoor
 */
public class FolderCoverage extends Coverable {
  private String folderName;
  private Map<File, FileCoverage> files = new HashMap<File, FileCoverage>();
  private int uncoveredLines = 0;

  /**
   * Adds a class coverage in the project
   * 
   * @param classCoverage
   */
  public void addFile(FileCoverage fileCoverage) {
    File file = fileCoverage.getFile();
    files.put(file, fileCoverage);
  }

 
  /**
   * Summarizes the coverage
   */
  @Override
  public void summarize() {
    countLines = uncoveredLines;
    for (FileCoverage fileCoverage : files.values()) {
      countLines += fileCoverage.getCountLines();
      coveredLines += fileCoverage.getCoveredLines();
    }
  }

  @Override
  public String toString() {
    return "Folder(name=" + folderName + ", coverage=" + getCoverage()
        + ", lines=" + countLines + ", covered=" + coveredLines + ")";
  }

  public String getFolderName() {
    return folderName;
  }

  public void setFolderName(String folderName) {
    this.folderName = folderName;
  }

}