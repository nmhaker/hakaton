package commands;

import commands.enums.Direction;
import commands.enums.Resource;

public class DropResourceCommand implements Command {
    private Resource resource;

    public DropResourceCommand(Resource resource) {
        this.resource = resource;
    }


    @Override
    public String getCommandCode() {
        return "l" + CommandUtils.getResource(resource);
    }

}
