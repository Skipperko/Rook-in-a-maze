package sk.stuba.fei.uim.oop.maze;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class MazeField{
    private Boolean topWall = true;
    private Boolean bottomWall = true;
    private Boolean leftWall = true;
    private Boolean rightWall = true;
    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) private Boolean visited = false;
    @NonNull private int row;
    @NonNull private int column;


}
