package sth.app.teaching;

/** Messages for menu interactions. */
@SuppressWarnings("nls")
public final class Message {

  /**
   * @return prompt for discipline name
   */
  public static String requestDisciplineName() {
    return "Nome da disciplina: ";
  }

  /**
   * @return prompt for project name
   */
  public static String requestProjectName() {
    return "Nome do projecto: ";
  }

  /**
   * @return prompt for identifier
   */
  public static String requestPersonId() {
    return "Identificador da pessoa: ";
  }
  /** Prevent instantiation. */
  private Message() {
    // EMPTY
  }

}
