import com.nettee.board.adapter.out.mapper.BoardEntityMapper
import com.nettee.board.adapter.out.mapper.BoardEntityMapperImpl
import com.nettee.board.adapter.out.persistence.BoardCommandAdapter
import com.nettee.board.adapter.out.persistence.BoardJpaRepository
import com.nettee.board.adapter.out.persistence.entity.BoardEntity
import com.nettee.board.application.domain.Board
import com.nettee.board.application.domain.type.BoardStatus
import com.nettee.board.application.port.BoardCommandPort
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import java.time.Instant

class BoardCommandPortTest : FreeSpec({
    lateinit var boardCommandPort: BoardCommandPort
    lateinit var boardJpaRepository: BoardJpaRepository
    lateinit var boardEntityMapper: BoardEntityMapper

    beforeTest{
        boardJpaRepository = mockk(relaxed = true)
        boardEntityMapper = mockk(relaxed = true)
        boardCommandPort = mockk(relaxed = true)

        every { boardEntityMapper.toEntity(any()) } answers {
            mockk<BoardEntity>(relaxed = true).apply {
                every { id } returns 1L
                every { title } returns firstArg<Board>().title
                every { content } returns firstArg<Board>().content
                every { status } returns BoardStatus.ACTIVE
                every { createdAt } returns Instant.now()
            }
        }
        every { boardJpaRepository.save(any()) } answers {
            firstArg()
        }
        every { boardEntityMapper.toDomain(any()) } answers {
            val entity = firstArg<BoardEntity>()
            Board().apply {
                id = entity.id
                title = entity.title
                content = entity.content
                status = entity.status
                createdAt = entity.createdAt
            }
        }
        every { boardCommandPort.createBoard(any()) } answers {
            val board = firstArg<Board>()
            val entity = boardEntityMapper.toEntity(board) // Mapper 호출
            val savedEntity = boardJpaRepository.save(entity) // 저장된 BoardEntity
            boardEntityMapper.toDomain(savedEntity)
        }

    }

    "BoardCommandPort should be created correctly" {
        boardCommandPort shouldNotBe null // 포트가 올바르게 생성되었는지 확인
    }

    "BoardCommandPort should be save Board correctly " {
        val board = Board().apply {
            title = "Test Title"
            content = "Test Content"
        }
        val boardPortTest = boardCommandPort.createBoard(board)

        boardPortTest.id shouldBe 1L
        boardPortTest.title shouldBe board.title
        boardPortTest.content shouldBe board.content
        boardPortTest.status shouldBe BoardStatus.ACTIVE
        boardPortTest.createdAt shouldNotBe null
    }

})