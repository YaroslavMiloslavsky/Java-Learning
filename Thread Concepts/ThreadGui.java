import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ThreadGui extends JPanel {
	private JButton btnPrintThreads,btnBallz, btnClear, btnSleep, btnSleep2, btnTransaction, btnBuffer;
	private JTextArea txtArea;
	private static final SecureRandom randomNumGenerator =  new SecureRandom();
	
	public ThreadGui() {
		txtArea = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		btnPrintThreads = new JButton("Print Test");
		btnBallz = new JButton("Ball Game");
		btnClear = new JButton("Clear");
		btnSleep = new JButton("Sleep1");
		btnSleep2 = new JButton("Sleep2"); // With execution pool
		btnTransaction = new JButton("Transaction");
		btnBuffer = new JButton("Consumer and Producer");
		
		this.setLayout(new BorderLayout());
		ActionListener cmd = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnPrintThreads) {
					PrintThread t1 = new PrintThread("Thread 1",txtArea);
					PrintThread t2 = new PrintThread("Thread 2",txtArea);
					t1.start();
					t2.start();
				}
				if(e.getSource() == btnClear) {
					txtArea.setText(null);
				}
				if(e.getSource() == btnSleep) {
					SleepThread s1 = new SleepThread("Thread 1",randomNumGenerator.nextInt(5000),txtArea);
					SleepThread s2 = new SleepThread("Thread 2",randomNumGenerator.nextInt(5000),txtArea);
					s1.start();
					s2.start();
				}
				if(e.getSource() == btnSleep2) {
					SleepThreadPool sp1 = new SleepThreadPool("Exec Thread 1",randomNumGenerator.nextInt(5000),txtArea);
					SleepThreadPool sp2 = new SleepThreadPool("Exec Thread 2",randomNumGenerator.nextInt(5000),txtArea);
					SleepThreadPool sp3 = new SleepThreadPool("Exec Thread 3",randomNumGenerator.nextInt(5000),txtArea);
					
					ExecutorService executionPool = Executors.newFixedThreadPool(2); // only 2 threads each time
					executionPool.execute(sp1);
					executionPool.execute(sp2);
					executionPool.execute(sp3);
					
					executionPool.shutdown();
				}
				if(e.getSource() == btnBallz) {
					MovingBallzGUI gui = new MovingBallzGUI();
					JFrame frame = new JFrame();
					frame.setSize(500,500);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.add(gui);
					frame.setVisible(true);
				}
				if(e.getSource() == btnTransaction) {
					Account account = new Account();
					ExecutorService threadPool = Executors.newCachedThreadPool();
					TransactionThread t1 = new TransactionThread(account);
					GetBalanceThread  t2 = new GetBalanceThread(account, txtArea);
					
					threadPool.execute(t1);
					threadPool.execute(t2);
					threadPool.shutdown();
				}
				if(e.getSource() == btnBuffer) {
					ExecutorService es = Executors.newCachedThreadPool();
//					Buffer sharedBuffer = new UnsichronizedBuffer(txtArea);
//					es.execute(new Prodeucer(sharedBuffer, txtArea));
//					es.execute(new Consumer(sharedBuffer, txtArea));
//					
//					es.shutdown();
					
//					Buffer sharedBlockedBuffer = new BlockingBuffer(txtArea);
//					es.execute(new Prodeucer(sharedBlockedBuffer, txtArea));
//					es.execute(new Consumer(sharedBlockedBuffer, txtArea));
//					
//					es.shutdown();
					
					Buffer sharedBuffer = new SynchronizedBuffer(txtArea);
					es.execute(new Prodeucer(sharedBuffer, txtArea));
					es.execute(new Consumer(sharedBuffer, txtArea));
					es.shutdown();
				}
			}
		};
		
		btnPrintThreads.addActionListener(cmd);
		btnClear.addActionListener(cmd);
		btnSleep.addActionListener(cmd);
		btnSleep2.addActionListener(cmd);
		btnBallz.addActionListener(cmd);
		btnTransaction.addActionListener(cmd);
		btnBuffer.addActionListener(cmd);
		
		//main text area
		txtArea.setEditable(false);
		txtArea.setFont(txtArea.getFont().deriveFont(Font.BOLD, 14f));
		add(scrollPane, BorderLayout.CENTER);
		
		// control panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,2));
		northPanel.add(btnPrintThreads);
		northPanel.add(btnBallz);
		northPanel.add(btnSleep);
		northPanel.add(btnSleep2);
		northPanel.add(btnTransaction);
		northPanel.add(btnBuffer);
		northPanel.add(btnClear);
		this.add(northPanel , BorderLayout.NORTH);
		
	}
}
