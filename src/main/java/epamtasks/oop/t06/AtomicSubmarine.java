package epamtasks.oop.t06;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtomicSubmarine {

    private static final Logger log = LogManager.getLogger(AtomicSubmarine.class);
    private AtomicEngine myEngine;

     private String name;

     private int serialNumber;

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

    public AtomicSubmarine(){
         myEngine = new AtomicEngine();
         name = "default sub";
         serialNumber = -1;

     }

     public AtomicSubmarine(String name){
         this();
         this.name = name;
     }

     public AtomicSubmarine(int serialNumber){
         this();
         this.serialNumber = serialNumber;
     }

     public AtomicSubmarine(String name, int serialNumber){
         this(name);
         this.serialNumber = serialNumber;
     }

     public void  prepareEngine(){
         if(myEngine.getStatus().equals(EngineStatus.STOPPED)){
             log.info("Engine status:{}",myEngine.getStatus());
             myEngine.startEngine();
         }
         log.info("Engine status:{}",myEngine.getStatus());
     }

     public void startMoving(){
         log.info("{}  begin moving.",this.getFullName());
     }

     public void stopMoving(){
        log.info("{} stopped moving.",this.getFullName());
    }

     public void emergencyStop(){
        log.info("Begin emergency stopping!");
        for(int i =5; i>0; i--){

            log.info("Emergency stop in {} ...",i);
        }
        myEngine.stopEngine();
        log.info("{} stopped!",this.getFullName());
    }

}
