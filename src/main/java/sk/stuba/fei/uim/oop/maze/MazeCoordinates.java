package sk.stuba.fei.uim.oop.maze;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class MazeCoordinates {
    @NonNull private int row;
    @NonNull private int column;
}
