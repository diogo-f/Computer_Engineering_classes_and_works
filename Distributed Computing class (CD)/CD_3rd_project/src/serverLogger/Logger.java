package serverLogger;

import publicInfo.InitialData;
import publicInfo.States;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Computacao Distribuida 2014/2015
 * <p>
 * Licenciatura em Engenharia Informatica
 * <p>
 * DETI - Universidade de Aveiro, Portugal
 * <p>
 * <p>
 * Base code for the Logger class of Scenes Of Life of SantaClaus
 *
 */
public class Logger {

    // Parameters
    private final int nReindeers;     //* total number of reindeers
    private final int nElves;     //* total numner of elves
    private final int groupSize;      //* size of group of elves that simultaneously falars to Santa Claus
    private String santaState;     //* Santa Claus state
    private String[] elvesState;       //* array indexed by elf id that stores each elf state
    private String[] reindeersState;       //* array indexed by reindeer id that stores each reindeer state
    private int reindeersAtStable;      //* number of reindeers currently at the stable
    private int nElvesInGroup;      //* number of elves currently in the group
    private boolean reindeerReady;      //* all reindeers are at the stable and ready for the viagem
    private boolean elvesKnocked;       //* groupSize elves have formed a group and are ready to falar to Santa Claus
    private ArrayList<Integer> queue;        //* information in contents of queue of elves waiting to enter the house
    private int queueSize;      //* information in size of queue of elves waiting to enter the house
    private Semaphore mutex;
    private boolean endReindOperationS;
    private boolean[] endElfOperations;
    private int endElf;
    private int end;
    private boolean santaEnd;

    /**
     * the Logger should use printw to write all log information (must be
     * initialized in constructor)
     */
    private final PrintWriter printw;

    public Logger() throws FileNotFoundException {
        this.nReindeers = InitialData.nReindeers;
        this.nElves = InitialData.nElfs;
        this.groupSize = InitialData.maxElvesInGroup;
        this.santaState = States.REST.toString();
        this.elvesState = new String[nReindeers];
        for (int i = 0; i < elvesState.length; i++) {
            elvesState[i] = States.WORK.toString();
        }
        this.reindeersState = new String[nReindeers];
        for (int i = 0; i < reindeersState.length; i++) {
            reindeersState[i] = States.HOLI.toString();
        }
        this.reindeersAtStable = 0;
        this.nElvesInGroup = 0;
        this.reindeerReady = false;
        this.elvesKnocked = false;
        this.endElf = 0;
        this.endReindOperationS = false;
        this.endElfOperations = new boolean[InitialData.nElfs];
        for (int i = 0; i < nElves; i++) {
            endElfOperations[i] = false;
        }
        this.mutex = new Semaphore(1);
        queue = new ArrayList<>();
        queueSize = 0;
        this.end = 0;
        this.santaEnd = false;
        File file = new File("logger");
        this.printw = new PrintWriter(file);
        header();
        write();
    }

//////////////////////WRITE/////////////////////
    public void writeElfState(int id, int state) {
        elvesState[id] = States.getStringFromInt(state);
        //elvesState[id] = state.toString();
        write();
    }

    public void writeReindeerState(int id, int state) {
        reindeersState[id] = States.getStringFromInt(state);
        //reindeersState[id] = state.toString();
        write();
    }

    public void writeSantaState(int state) {
        santaState = States.getStringFromInt(state);
        //santaState = state.toString();
        write();
    }

    /////////////////////////SET/////////////////////
    public void reindeersAtStable(int i) {
        try {
            mutex.acquire();
            this.reindeersAtStable += i;
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void setReindeerReady(boolean r) {
        try {
            mutex.acquire();
            if (r == false) {
                header();       //escrever o header a cada ano que passa.
            }
            this.reindeerReady = r;
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void nElvesInGroup(int i) {
        try {
            mutex.acquire();
            this.nElvesInGroup += i;
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void elvesKnocked(boolean k) {
        this.elvesKnocked = k;
    }

    public void queueAdd(int id) {
        try {
            mutex.acquire();
            this.queue.add(id);
            this.queueSize++;
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void queueEmpty() {
        try {
            mutex.acquire();
            this.queueSize = 0;
            this.queue.clear();
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void endReindeerOperation(boolean end) {
        this.endReindOperationS = end;
    }

    public boolean endReindeerOperation() {
        return endReindOperationS;
    }

    public void endElfOperations(int endID) {
        this.endElfOperations[endID] = true;
        end++;
        if (end == InitialData.nElfs) {
            santaEnd = true;
        }
    }

    public boolean endElfOperation(int id) {
        return endElfOperations[id];
    }

    public boolean endElfsOperation() {
        return santaEnd;
    }

    public void getHeader() {
        header();
    }

    /**
     *
     *
     * Writes the header of the log file to the printw PrintWriter
     */
    private void header() {
        printw.format("%5s", "SANTA");
        for (int e = 0; e < nElves; e++) {
            printw.format(" ELF %d", e);
        }
        printw.format(" %3s %2s", "NEG", "SG");
        for (int q = 0; q < groupSize; q++) {
            printw.format(" Q%d", q);
        }

        for (int r = 0; r < nReindeers; r++) {
            printw.format(" REIND %d", r);
        }
        printw.format(" %3s %2s", "NRD", "SR");

        printw.format("\n");

        printw.flush();
    }

    /**
     * Writes a new line with all the information to the printw PrintWriter
     */
    private synchronized void write() {
        printw.format("%5s", santaState);
        for (int e = 0; e < nElves; e++) {
            printw.format("%6s", elvesState[e]);
        }
        printw.format("%3d ", nElvesInGroup);

        printw.format("%3d", (elvesKnocked ? 1 : 0));

        for (int q = 0; q < queueSize; q++) {
            printw.format("%3d", queue.get(q));
        }
        for (int q = queueSize; q < groupSize; q++) {
            printw.format("%3s", "-");
        }

        for (int r = 0; r < nReindeers; r++) {
            printw.format("%8s", reindeersState[r]);
        }

        printw.format("%3d ", reindeersAtStable);
        printw.format("%3d ", (reindeerReady ? 1 : 0));
        printw.format("\n");

        printw.flush();
    }
}
