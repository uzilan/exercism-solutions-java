public class Robot {
    private GridPosition gridPosition;
    private Orientation orientation;

    public Robot(final GridPosition gridPosition, final Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public void turnLeft() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public void turnRight() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public void advance() {
        final var x = gridPosition.x;
        final var y = gridPosition.y;
        switch (orientation) {
            case NORTH:
                gridPosition = new GridPosition(x, y + 1);
                break;
            case EAST:
                gridPosition = new GridPosition(x + 1, y);
                break;
            case SOUTH:
                gridPosition = new GridPosition(x, y - 1);
                break;
            case WEST:
                gridPosition = new GridPosition(x - 1, y);
                break;
        }
    }

    public void simulate(final String instructions) {
        instructions.codePoints()
                .mapToObj(i -> (char) i)
                .forEach(c -> {
                    switch (c) {
                        case 'R':
                            turnRight();
                            break;
                        case 'L':
                            turnLeft();
                            break;
                        case 'A':
                            advance();
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                });
    }
}
