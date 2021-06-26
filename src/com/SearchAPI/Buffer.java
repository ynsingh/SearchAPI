package com.SearchAPI;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

/**
 * This class is used to create object of RoutingManagerBuffer.
 * There are inputRoutingBuffer and outputRoutingBuffer.
 */

    private static final List<File> inputBuffer = new LinkedList<>();
    private static final List<File> outputBuffer = new LinkedList<>();
    private static final ReentrantLock inputBufferLock = new ReentrantLock();
    private static final ReentrantLock outputBufferLock = new ReentrantLock();
    private static Buffer Buffer;

    /**
     * This is the default constructor of the class.
     * However this is made private so that it cannot be accessed from outside the class.
     */
    private Buffer() {
    }

    /**
     * @return - Object of RoutingManagerBuffer.
     * This is made singleton object as only one instance can be accessed.
     */
    public static Buffer getInstance() {
        if (Buffer == null) {
            Buffer = new Buffer();
        }
        return Buffer;
    }

    /**
     * @return - object of inputRoutingBuffer
     */
    private List<File> Buffer() {
        return inputBuffer;
    }

    /**
     * @param file - The File object is given as input argument.
     * @return - boolean value true if the file is added successfully.
     */
    boolean addToInputBuffer(File file) {
        inputBufferLock.lock();
        inputBuffer.add(file);
        //file.deleteOnExit();
        //file.delete();
        inputBufferLock.unlock();
        return true;
    }

    /**
     * This method is used to fetch file from the inputBuffer one by one.
     *
     * @return - File
     */
    File fetchFromInputBuffer() {
        inputBufferLock.lock();
        File file = null;
        try {
            file = inputBuffer.get(0);
            inputBuffer.remove(0);

        } catch (Exception e) {
        }
        inputBufferLock.unlock();
        return file;
    }

    /**
     * @param file - File object is given as input argument.
     * @return - true if the file is added successfully.
     */
    boolean addToOutputBuffer(File file) {
        outputBufferLock.lock();
        outputBuffer.add(file);
        file.deleteOnExit();
        outputBufferLock.unlock();
        return true;
    }

    /**
     * This method is used to fetch file from the outputBuffer.
     *
     * @return - File.
     */
    File fetchFromOutputBuffer() {
        outputBufferLock.lock();
        File file = null;
        try {
            file = outputBuffer.get(0);
            outputBuffer.remove(0);
        } catch (Exception e) {
        }
        outputBufferLock.unlock();
        return file;
    }


    /**
     * @param file File needs to be added to the inputBuffer.
     * @return True if file is added successfully.
     */
    public boolean addFileToInputBuffer(File file) {
        boolean isAdded = false;
        isAdded = Buffer.addToInputBuffer(file);
        return isAdded;
    }

    /**
     * @param file File needs to be added to the outputBuffer.
     * @return True if file is added successfully.
     */
    public boolean addFileToOutputBuffer(File file) {
        boolean isAdded = false;
        isAdded = Buffer.addToOutputBuffer(file);
        return isAdded;
    }

    /**
     * This method is used to fetch file from the input buffer one by one.
     */
    public void getFileFromInputBuffer() {
        File file = Buffer.fetchFromInputBuffer();
        if (!(file == null)) {
            //System.out.println(file);
            //readFile.readIncomingFile(file);
            QueryManager q = new QueryManager();
            q.readIncomingFile(file);
        }
    }

    int sizeofInputBuffer() {
            int s = inputBuffer.size();
            return s;
    }
    public File getFileFromOutputBuffer() {
        return Buffer.fetchFromOutputBuffer();
    }
}
