package  com._27Mikael.physiology.kinesis.endurance;

import com._27Mikael.physiology.events.PlayerEventRetriever;

public interface IEndurance {

  Default(Endurance.class, Endurance::new, null)

  Type(Class<? extends IEndurance> clas, Function<PlayerEntity, IEndurance> constructor, BiConsumer<ServerPlayerEntity, Integer> serverEnduranceHandler) {
    this.constructor = constructor;
    this.clas = clas;
    this.serverEnduranceHandler = serverEnduranceHandler;
  }

  private final Function<PlayerEntity, IEndurance> constructor;
  private 

}
