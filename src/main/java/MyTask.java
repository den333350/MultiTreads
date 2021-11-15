 class MyTask implements Runnable{
    int startFrom;
    Server server;

    MyTask(int startFrom,Server server){
        this.startFrom = startFrom;
        this.server = server;
    }

    public void  run() {
        synchronized (server.monitor) {

            for (int i = startFrom; i <= 50; i += 2) {

                server.addNumber(i);
                try {
                    server.monitor.notifyAll();
                    if(i<49)
                        server.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
