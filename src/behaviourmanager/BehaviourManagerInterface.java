package behaviourmanager;

import behaviour.Behaviour;

public interface BehaviourManagerInterface {

    public void update();

    private void updateTacticState() {

    }

    public Behaviour getNextBehaviour();

}
