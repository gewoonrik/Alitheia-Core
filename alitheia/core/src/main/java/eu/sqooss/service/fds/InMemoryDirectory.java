package eu.sqooss.service.fds;

import eu.sqooss.service.db.ProjectFile;

import java.util.List;

public interface InMemoryDirectory {

    public String getName();
    public String getPath();
    public InMemoryDirectory getParentDirectory();
    public InMemoryCheckout getCheckout();
    public List<InMemoryDirectory> getSubDirectories();
    public ProjectFile getFile(String name);
    public List<ProjectFile> getFiles();
    public List<String> getFileNames();
    public boolean pathExists(String path);
    public void addFile(String path);
    public void deleteFile(String path);
    public InMemoryDirectory getSubdirectoryByName(String path);
    public InMemoryDirectory createSubDirectory(String name);
    public String toString(int indentation);

}

