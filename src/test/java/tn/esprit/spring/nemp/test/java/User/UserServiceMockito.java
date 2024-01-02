package tn.esprit.spring.nemp.test.java.User;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Disabled;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.boot.test.context.SpringBootTest;
        import tn.esprit.spring.nemp.Entities.User;
        import tn.esprit.spring.nemp.Repositorys.UserRepository;
        import tn.esprit.spring.nemp.Services.UserService;

        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.when;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.doNothing;
        import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class UserServiceMockito {

    @InjectMocks
    private UserService stockService;

    @Mock
    private UserRepository stockRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllStocks() {
        List<User> stocks = new ArrayList<>();
        stocks.add(new User(1, "12345678", 98765432, "John", "Doe", "example@example.com"));
        stocks.add(new User(2, "12345679", 98765462, "rihab", "nabli", "nabli.rihab@rspri.tn"));

        when(stockRepository.findAll()).thenReturn(stocks);

        List<User> result = stockService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddStock() {
        User stock = new User(1, "12345678", 98765432, "John", "Doe", "example@example.com");

        when(stockRepository.save(stock)).thenReturn(stock);

        User result = stockService.createUser(stock);

        assertEquals("Doe", result.getPrenom());
    }


    @Test
    public void testDeleteStock() {
        int stockIdToDelete = 1;

        doNothing().when(stockRepository).deleteById(stockIdToDelete);


        stockService.deleteUser(stockIdToDelete);

        verify(stockRepository, Mockito.times(1)).deleteById(stockIdToDelete);
    }
    @Test
    @Disabled
    public void testUpdateStock() {
        User stockToUpdate = new User(1, "12345678", 98765432, "John", "Doe", "example@example.com");

        when(stockRepository.save(stockToUpdate)).thenReturn(stockToUpdate);

        User updatedStock = stockService.updateUser(stockToUpdate.getIduser(),stockToUpdate);

        assertEquals("UpdatedStock", updatedStock.getPrenom());
        assertEquals("12345678", updatedStock.getCin());
        assertEquals(98765432, updatedStock.getTel());

        Mockito.verify(stockRepository, Mockito.times(1)).save(stockToUpdate);
    }
}