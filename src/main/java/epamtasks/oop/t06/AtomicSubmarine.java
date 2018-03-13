package epamtasks.oop.t06;


import epamtasks.oop.t07.Submarine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Submarine(nuberOfEngines = 1,useAmomicPower = true)
public class AtomicSubmarine {

    private static final Logger log = LogManager.getLogger(AtomicSubmarine.class);
    private final AtomicEngine myEngine;
    private String name;
    private final int serialNumber;

    private static int submarineCounter = 1;

     class AtomicEngine{

         private EngineStatus status;
         private AtomicEngine(){status =  EngineStatus.STOPPED;}

         public EngineStatus getStatus() {
             return status;
         }

         private void stopEngine(){
             status = EngineStatus.STOPPED;log.info("Engine is stopped!");
         }
         private void startEngine(){
             status = EngineStatus.RUNNING;
             log.info("Engine is started!");
         }
     }

    public AtomicSubmarine(){
        myEngine = new AtomicEngine();
        name = "UNKNOWN";
        serialNumber = submarineCounter++;

    }

    public AtomicSubmarine setName(String name) {
        this.name = name;
        return  this;
    }

    public String getName() {
        return name;
    }
    public int getSerialNumber() {
        return serialNumber;
    }
    public String getFullName(){
        StringBuilder fullName = new StringBuilder();
        fullName.append("Submarine ")
                .append(getName())
                .append(" with serial number #")
                .append(getSerialNumber());

         return  fullName.toString();
    }


     public AtomicSubmarine  prepareEngine(){
         if(myEngine.getStatus().equals(EngineStatus.STOPPED)){
             log.info("Engine status:{}",myEngine.getStatus());
             myEngine.startEngine();
         }
         log.info("Engine status:{}",myEngine.getStatus());
         return  this;
     }
     public AtomicSubmarine startMoving(){
         log.info("{}  begin moving.",this.getFullName());
         return this;
     }
     public AtomicSubmarine stopMoving(){
        log.info("{} stopped moving.",this.getFullName());
        return this;
    }
     public AtomicSubmarine emergencyStop(){
        log.info("Begin emergency stopping!");
        for(int i =5; i>0; i--){

            log.info("Emergency stop in {} ...",i);
        }
        myEngine.stopEngine();
        log.info("{} stopped!",this.getFullName());
        return this;
    }

}
