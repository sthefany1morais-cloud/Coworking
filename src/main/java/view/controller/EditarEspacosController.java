package view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import execoes.EspacoComReservasAtivasException;
import model.espacos.Espaco;
import service.EspacoService;
import service.ReservaService;
import util.FiltroUtil;
import util.MensagemUtil;
import util.TabelaUtil;
import util.VerificacaoUtil;
import view.MainCoworking;

public class EditarEspacosController {
    @FXML private ComboBox<String> filtroTipoComboBox;
    @FXML private TextField buscaField;
    @FXML private TableView<Espaco> espacosTableView;
    @FXML private TableColumn<Espaco, Integer> idColumn;
    @FXML private TableColumn<Espaco, String> nomeColumn;
    @FXML private TableColumn<Espaco, String> tipoColumn;
    @FXML private TableColumn<Espaco, Integer> capacidadeColumn;
    @FXML private TableColumn<Espaco, Double> precoColumn;
    @FXML private TableColumn<Espaco, Boolean> disponivelColumn;
    @FXML private Button editarButton;
    @FXML private Button excluirButton;
    @FXML private Button voltarButton;
    @FXML private CheckBox disponiveisCheckBox;
    @FXML private CheckBox indisponiveisCheckBox;
    @FXML private Label mensagemLabel;
    @FXML private Label errosLabel;

    private MainCoworking mainApp;
    private EspacoService espacoService;
    private ReservaService reservaService;
    private ObservableList<Espaco> espacosList;
    private FilteredList<Espaco> filteredList;

    public void setMainApp(MainCoworking mainApp) {
        this.mainApp = mainApp;
    }

    public void setEspacoService(EspacoService espacoService) {
        this.espacoService = espacoService;
        carregarEspacos();
    }

    public void setReservaService(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @FXML
    private void initialize() {
        filtroTipoComboBox.setItems(FXCollections.observableArrayList("Todos", "Sala de Reunião", "Cabine Individual", "Auditório"));
        filtroTipoComboBox.setValue("Todos");
        TabelaUtil.configurarColunasEspacos(espacosTableView, idColumn, nomeColumn, tipoColumn, capacidadeColumn, precoColumn, disponivelColumn);
        buscaField.textProperty().addListener((obs, oldText, newText) -> filtrar());
        filtroTipoComboBox.setOnAction(e -> filtrar());
        disponiveisCheckBox.setOnAction(e -> filtrar());
        indisponiveisCheckBox.setOnAction(e -> filtrar());
        editarButton.disableProperty().bind(espacosTableView.getSelectionModel().selectedItemProperty().isNull());
        excluirButton.disableProperty().bind(espacosTableView.getSelectionModel().selectedItemProperty().isNull());
        disponiveisCheckBox.setSelected(true);
        indisponiveisCheckBox.setSelected(true);
    }

    private void carregarEspacos() {
        espacosList = FXCollections.observableArrayList(espacoService.listarExistentes());
        filteredList = new FilteredList<>(espacosList, p -> true);
        espacosTableView.setItems(filteredList);
        mensagemLabel.setText(VerificacaoUtil.verificarEspacos(espacoService));
    }

    private void filtrar() {
        FiltroUtil.aplicarFiltroEspacos(filteredList, buscaField.getText().toLowerCase(),
                filtroTipoComboBox.getValue(), disponiveisCheckBox.isSelected(),
                indisponiveisCheckBox.isSelected());
    }

    @FXML
    private void editar() {
        Espaco selecionado = espacosTableView.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            EditarEspacoDetalhesController.setEspacoSelecionado(selecionado);
            mainApp.mudarScene("EditarEspacoDetalhes.fxml");
        }
    }

    @FXML
    private void excluir() {
        Espaco selecionado = espacosTableView.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                boolean possuiReservas = reservaService.possuiReservasAtivas(selecionado);
                espacoService.removerEspaco(selecionado.getId(), possuiReservas);
                MensagemUtil.mostrarAlertaInformacao("Sucesso", "Espaço removido!");
                carregarEspacos();
            } catch (EspacoComReservasAtivasException e) {
                MensagemUtil.mostrarAlertaErro("Erro", "Erro: " + e.getMessage());
            } catch (Exception e) {
                MensagemUtil.mostrarAlertaErro("Erro", "Erro inesperado: " + e.getMessage());
            }
        }
    }

    @FXML
    private void voltar() {
        mainApp.mudarScene("MenuEspacos.fxml");
    }
}
